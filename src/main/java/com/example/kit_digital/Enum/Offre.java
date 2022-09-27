package com.example.kit_digital.Enum;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Offre {

    GOLD("Gold","2000dt"),
    SILVER("Silver","1500dt"),
    BRONZE("Bronze","800dt");

    private final String title ;
    private final String description ;

    Offre(String title,String description) {
        this.title=title;
        this.description = description;
    }

//    @JsonValue
//    public String getDescription (){
//        return description;
//    }
    @JsonValue
    public String getTitle (){
        return title;
    }

}
