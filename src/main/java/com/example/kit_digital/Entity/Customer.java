package com.example.kit_digital.Entity;

import com.example.kit_digital.Enum.Origin;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String userName;
    @Enumerated(EnumType.STRING)
    private Origin origin;
    @Email
    private String email;
    private String civility;
    private Date birthday;
    private Boolean cgu;
    private String password;
    @JsonIgnore
    private String verificationCode;
    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Compt> compt;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;



}
