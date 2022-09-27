package com.example.kit_digital.Enum;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Revenus {

    VERYLOW("Inférieur à 200 TND"),
    LOW("Entre 200 TND et 450 TND"),
    NORMAL("Entre 450 TND et 1000 TND"),
    HEIGHT("Entre 1000 TND et 2000 TND"),
    VERYHEIGHT("Supérieur à 2000 TND");
            ;
    private final String description ;

    Revenus(String description) {
        this.description = description;
    }
    @JsonValue
    public String getDescription (){
        return description;
    }

}
