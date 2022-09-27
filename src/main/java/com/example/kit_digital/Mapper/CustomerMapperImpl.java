package com.example.kit_digital.Mapper;

import com.example.kit_digital.Dto.CustomerDto;
import com.example.kit_digital.Entity.Customer;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CustomerMapperImpl implements CustomerMapper {


    private final ModelMapper modelMapper;



    @Override
    public Customer toEntity(CustomerDto customerDto) {
        Customer customer = (Customer) this.modelMapper.map(customerDto, Customer.class);
        return customer;
    }

    @Override
    public List<Customer> toEntity(List<CustomerDto> customersDto) {
        List<Customer>customers= customersDto.stream().map(customerDto ->this.modelMapper.map(customerDto,Customer.class)).collect(Collectors.toList());
        return customers;
    }

    @Override
    public CustomerDto toDTO(Customer customer) {
        CustomerDto customerDto = (CustomerDto) this.modelMapper.map(customer, CustomerDto.class);
        return customerDto;
    }

    @Override
    public List<CustomerDto> toDTO(List<Customer> customers) {
        List<CustomerDto>customersDto= customers.stream().map(customer ->this.modelMapper.map(customer,CustomerDto.class)).collect(Collectors.toList());
        return customersDto;
    }


}
