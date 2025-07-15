package com.hugo83.webmall.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberFormDto {

    @NotBlank(message = "이름은 필수입니다")
    private String name;

    @NotBlank(message = "이메일은 필수입니다")
    @Email(message = "이메일 형식으로 입력해주세요")
    private String email;

    @NotEmpty(message = "비밀번호는 필수입니다")
    @Length(min = 6, max = 16, message = "비밀번호는 6자 이상, 16자 이하로 입력하세요")
    private String password;

    @NotEmpty(message = "주소는 필수입니다")
    private String address;
} 
