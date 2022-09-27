package com.example.kit_digital.Mapper;

import com.example.kit_digital.Dto.CustomerDto;
import com.example.kit_digital.Entity.Customer;

import java.util.List;

public interface CustomerMapper {
    Customer toEntity(CustomerDto customerDto);
    List<Customer> toEntity( List<CustomerDto> customersDto);
    CustomerDto toDTO(Customer customer);
    List<CustomerDto> toDTO( List<Customer> customers);
}
