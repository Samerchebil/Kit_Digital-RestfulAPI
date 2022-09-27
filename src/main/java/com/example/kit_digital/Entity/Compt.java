package com.example.kit_digital.Entity;

import com.example.kit_digital.Enum.Married;
import com.example.kit_digital.Enum.Offre;
import com.example.kit_digital.Enum.Revenus;
import com.example.kit_digital.Enum.Statu;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Compt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Offre offre;

    private Married married;

    @Size(min = 2, max = 20)
    private String city;

    @Size(min = 2, max = 20)
    private String birthCity;

    private String birthCountry;

    private String countryLiveIn;

    @Size(min = 2, max = 80)
    private String address;

    @Max(6)
    private Integer postalCard;

    private Integer cin;

    private Revenus revenus;

    private Statu statu;

    @ManyToOne
    private Customer customer;

}

