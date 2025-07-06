package com.hugo83.webmall.config;

import lombok.Getter;

@Getter
public enum MemberRole {
    ADMIN("ROLE_ADMIN"),  // 공통코드와 유사하게 
    USER("ROLE_USER");

    MemberRole(String value) {
        this.value = value;
    }

    private String value;
}