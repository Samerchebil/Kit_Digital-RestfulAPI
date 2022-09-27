package com.example.kit_digital.Enum;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Married {

    CELIB("Célibataire"),
    MARIE("Marié"),
    DIVORCE("Divorcé"),
    VEUF("Veuf");
    private final String description ;
    Married(String description) {
        this.description = description;
    }
    @JsonValue
    public String getDescription (){
        return description;
    }

}
