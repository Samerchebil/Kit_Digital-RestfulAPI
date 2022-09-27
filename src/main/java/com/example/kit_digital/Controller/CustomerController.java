package com.example.kit_digital.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.kit_digital.Dto.CustomerDto;
import com.example.kit_digital.Entity.Customer;
import com.example.kit_digital.Entity.Role;
import com.example.kit_digital.Enum.RoleE;
import com.example.kit_digital.Exception.ApiRequestException;
import com.example.kit_digital.Mapper.CustomerMapper;
import com.example.kit_digital.Service.CustomerService;
import com.example.kit_digital.Service.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.mail.imap.Utility;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("api/costumers")
@SecurityRequirement(name = "bearerAuth")
@Slf4j
public class CustomerController  {
    private final CustomerServiceImpl customerService;
    private CustomerMapper customerMapper;


    @Autowired
    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @GetMapping("all")
    public ResponseEntity getCustomers()
    {
        List<CustomerDto> customers=customerService.getCustomers();
        if (customers.isEmpty())
        {
            throw new ApiRequestException("Oops can't find any customer");
        }
    return new ResponseEntity(customers, HttpStatus.ACCEPTED);


    }

    @PostMapping("save")
    public void saverCustomers(@RequestBody Customer customer) throws MessagingException, UnsupportedEncodingException {
              customerService.saveCustomer(customer);
            //customerService.sendVerificationEmail(customer);
    }

    @GetMapping("role/all")
    public ResponseEntity getRole()
    {
        return new ResponseEntity(customerService.getAllRoles(), HttpStatus.ACCEPTED);
    }

    @PostMapping("role/addtouser")
    public ResponseEntity<?>addToUser(@RequestBody RoleToCustomerForm form)
    {
        customerService.addRoleToCustomer(form.getUserName(),form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/token/refresh")
    public void  refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT =verifier.verify(refresh_token);
                String username= decodedJWT.getSubject();
                CustomerDto userDto = customerService.getCustomer(username);
                Customer user = customerMapper.toEntity(userDto);
                String access_token = JWT.create()
                        .withSubject(user.getUserName()) //subject something unique about the user , like the ID or username
                        .withExpiresAt(new Date(System.currentTimeMillis()+10*60*1000)) //10 minutes because in milliseconde
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles",user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token",access_token);
                tokens.put("refresh_token",refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),tokens);
            }
            catch (Exception e){
                log.error("Error logging in : {}",e.getMessage());
                response.setHeader("error ya samer",e.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message",e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),error);
            }
        }else
        {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
@Data
class RoleToCustomerForm{
    private String userName;
    private String roleName;
}