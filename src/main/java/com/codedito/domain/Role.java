package com.codedito.domain;

import lombok.ToString;

@ToString
public enum Role {
    ADMIN("ADMIN"), TEACHER("TEACHER"), STUDENT("STUDENT"), USER("USER");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
