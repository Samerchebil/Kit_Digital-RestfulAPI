package com.example.kit_digital.Enum;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Origin {

    TRT("Tunisien(e) résidant(e) en Tunisie"),
    TRE("Tunisien(e) résidant(e) à l’étranger"),
    ERT("Etranger(e) résidant(e) en Tunisie");
    private final String description ;

    Origin(String description) {
        this.description = description;
    }
    @JsonValue
    public String getDescription (){
        return description;
    }
}
