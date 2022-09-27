package com.example.kit_digital.Enum;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Activity {

    STUDENT("Student"),
    LIBERAL("Liberal profession"),
    ARTISAN("Artisan"),
    COMMERCANT("COMMERCANT"),
    EMPLOYEE("Employee"),
    RETIRMENT("Retirement"),
    PRIVATE("Private secteur"),
    OTHER("Other");
    private final String description ;
    Activity(String description) {
        this.description = description;
    }
    @JsonValue
    public String getDescription (){
        return description;
    }
}
