package com.stock.managementservice.domain;

public enum market {
    KOR("KOR"), US("US");

    private String value;

    market(String value){
        this.value=value;
    }
}
