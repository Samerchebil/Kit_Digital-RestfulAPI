package com.example.kit_digital.Controller;

import com.example.kit_digital.Entity.Compt;
import com.example.kit_digital.Entity.Customer;
import com.example.kit_digital.Enum.Statu;
import com.example.kit_digital.Service.ComptService;
import com.example.kit_digital.Service.CustomerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("api/compt")
@SecurityRequirement(name = "bearerAuth")
@Slf4j
public class ComptController {

    private ComptService comptService;
    private ComptService customerService;


    public ComptController(ComptService comptService){
            this.comptService=comptService;
    }

    @GetMapping("/all")
    public ResponseEntity getCompts()
    {
        return new ResponseEntity(comptService.getCompts(), HttpStatus.ACCEPTED);
    }
    @GetMapping("id/{id}")
    public Compt getCompt(@PathVariable("id") Long comptId) throws MessagingException, UnsupportedEncodingException
    {
      return comptService.getComptById(comptId);
    }

    @PostMapping("save/{id}")
    public void saveCompt(@PathVariable("id") Long customorId,@RequestBody Compt compt) throws MessagingException, UnsupportedEncodingException
    {
        comptService.saveCompt(compt,customorId);
    }


    @PutMapping("update/{ComptId}")
    public void updateStudent(
            @PathVariable("ComptId") Long ComptId,
            @RequestBody(required = false) Statu status
            ){
        comptService.UpdateCompt(ComptId,status);

            }


}
