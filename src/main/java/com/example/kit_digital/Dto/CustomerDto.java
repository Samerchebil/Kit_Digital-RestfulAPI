package com.example.kit_digital.Dto;


import com.example.kit_digital.Entity.Role;
import com.example.kit_digital.Enum.Origin;
import lombok.Data;

import java.util.Collection;
import java.util.Date;

@Data
public class CustomerDto {
    private Long id;
    private String name;
    private String userName;
    private Origin origin;
    private String civility;
    private String email;
    private Date birthday;
    private Collection<Role> roles;

}
