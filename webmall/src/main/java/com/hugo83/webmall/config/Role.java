package com.hugo83.webmall.config;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("ROLE_ADMIN"),  // 공통코드와 유사하게 
    USER("ROLE_USER");

    Role(String value) {
        this.value = value;
    }

    private String value;
}