package com.hugo83.webmall.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hugo83.webmall.entity.Member;
import com.hugo83.webmall.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;

    public Member saveMember(Member member) {
        validateDuplicateMember(member);

        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> opMember = memberRepository.findByEmail(member.getEmail());

        if (opMember.isPresent()) {
            throw new IllegalStateException("이미 가입한 회원입니다");
        }
    }
}
