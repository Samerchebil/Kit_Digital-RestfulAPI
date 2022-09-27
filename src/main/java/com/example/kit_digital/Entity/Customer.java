package com.example.kit_digital.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String userName;
    private String from;
    private String civility;
    private Date birthday;
    private Boolean cgu;
    @OneToOne(cascade = CascadeType.ALL)
    private Otp otp ;


}
