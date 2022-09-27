package com.example.kit_digital.Service;

import com.example.kit_digital.Dto.CustomerDto;
import com.example.kit_digital.Entity.Customer;
import com.example.kit_digital.Entity.Role;
import com.example.kit_digital.Mapper.CustomerMapper;
import com.example.kit_digital.Mapper.CustomerMapperImpl;
import com.example.kit_digital.Repo.CustomerRepo;
import com.example.kit_digital.Repo.RoleRepo;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CustomerServiceImpl implements CustomerService, UserDetailsService {


    private CustomerRepo customerRepo;
    private CustomerMapper customerMapper;
    private final RoleRepo roleRepo;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    public CustomerServiceImpl(CustomerRepo customerRepo,CustomerMapper customerMapper, RoleRepo roleRepo,PasswordEncoder passwordEncoder ){
        this.customerRepo=customerRepo;
        this.customerMapper=customerMapper;
        this.roleRepo=roleRepo;
        this.passwordEncoder=passwordEncoder;
    }
    @Override
    public void saveCustomer(Customer customer) {
        String randomCode = RandomString.make(10);
        customer.setVerificationCode(randomCode);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRepo.save(customer);
    }




//    public void sendVerificationEmail(Customer customer) throws MessagingException, UnsupportedEncodingException {
//    String subject = "please verify your registration";
//    String senderName= "Kit Digital Bank";
//    String mailContent = "<p>Dear "+ customer.getUserName()+",</p>";
//           mailContent +="<p>Please click the link below to verify to your registration</p>";
//           mailContent +="<p>Your Verification Code is "+customer.getVerificationCode()+" </p>";
//
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//        helper.setFrom("samerchebil18@gmail.com",senderName);
//        helper.setTo(customer.getEmail());
//        helper.setSubject(subject);
//        helper.setText(mailContent,true);
//        mailSender.send(message);
//
//    }

    @Override
    public CustomerDto getCustomer(String username) {
        Customer customer= customerRepo.findByUserName(username).get();
       return (customerMapper.toDTO(customer));

    }

    @Override
    public List<CustomerDto> getCustomers() {
        List<Customer> customers= customerRepo.findAll();
        return (customerMapper.toDTO(customers));
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToCustomer(String customerName, String roleName) {
        Optional<Customer> customer= customerRepo.findByUserName(customerName);
        Role role = roleRepo.findByName(roleName);
        customer.get().getRoles().add(role);
        customerRepo.save(customer.get());
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> user = customerRepo.findByUserName(username);
        if (user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the data base");
        }
        else {
            log.info("User found in the database: {}", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.get().getRoles().forEach(role->{
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.get().getUserName(), user.get().getPassword(), authorities);
        }
}
