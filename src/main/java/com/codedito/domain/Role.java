package com.codedito.domain;

import lombok.ToString;

@ToString
public enum Role {
    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
