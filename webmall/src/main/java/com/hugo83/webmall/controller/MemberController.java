package com.hugo83.webmall.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hugo83.webmall.config.Role;
import com.hugo83.webmall.dto.MemberFormDto;
import com.hugo83.webmall.entity.Member;
import com.hugo83.webmall.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {

    @SuppressWarnings("unused")
    private final MemberService memberService;

    @SuppressWarnings("unused")
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/new")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());

        return "member/memberForm";
    }

    @PostMapping("/new")
    public String postMemberForm(MemberFormDto memberFormDto) {
        Member member = Member.builder().name(memberFormDto.getName())
                              .email(memberFormDto.getEmail())
                              .address(memberFormDto.getAddress())
                              .role(Role.USER)
                              .build();

        memberService.saveMember(member);

        return "redirect:/";
    }
    
    
}
