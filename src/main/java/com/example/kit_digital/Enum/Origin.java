package com.example.kit_digital.Enum;

public enum From {

    TRT("Tunisien(e) résidant(e) en Tunisie"),
    TRE("Tunisien(e) résidant(e) à l’étranger"),
    ERT("Etranger(e) résidant(e) en Tunisie");
    private final String description ;

    From(String description) {
        this.description = description;
    }

    public String getDescription (){
        return description;
    }
}
