package com.example.kit_digital.Service;


import com.example.kit_digital.Dto.CustomerDto;
import com.example.kit_digital.Entity.Customer;
import com.example.kit_digital.Entity.Role;

import java.util.List;

public interface CustomerService {
    void saveCustomer(Customer customer);
    CustomerDto getCustomer(String username);
    List<CustomerDto>getCustomers();
    Role saveRole(Role role);
    List<Role> getAllRoles();

    void addRoleToCustomer(String username,String customerName);

}
