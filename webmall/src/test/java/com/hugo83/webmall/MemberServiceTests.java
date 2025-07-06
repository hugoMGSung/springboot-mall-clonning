package com.hugo83.webmall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hugo83.webmall.entity.Member;
import com.hugo83.webmall.service.MemberService;

@SpringBootTest
public class MemberServiceTests {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember() {
        return null;
    }
}
