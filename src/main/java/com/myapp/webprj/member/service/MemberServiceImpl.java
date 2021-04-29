package com.myapp.webprj.member.service;

import com.myapp.webprj.member.domain.Member;
import com.myapp.webprj.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;

    @Override
    public void signUp(Member member) {

        //평문 비번
        String rawPassword = member.getPassword(); //평문으로 넘어온 pw를 꺼냄
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        //암호화 비번
        String encodedPassword = encoder.encode(rawPassword); //평문 pw를 암호화
        member.setPassword(encodedPassword); //암호화된 pw를 저장함

        memberMapper.register(member); //암호화된 pw가 포함된 회원정보를 등록함
    }

    @Override
    public boolean isDuplicate(String kind, String info) {

        Map<String, Object> checkDataMap = new HashMap<>();
        checkDataMap.put("kind", kind);
        checkDataMap.put("info", info);

        //
        int resultNumber = memberMapper.isDuplicate(checkDataMap);

        return resultNumber  == 1;
    }

    @Override
    public Member getUser(String account) {
        return memberMapper.getUserInfo(account);
    }

    @Override
    public String login(Member inputData, Member dbData) {

        if (dbData != null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(inputData.getPassword(), dbData.getPassword())) {
                return "loginSuccess";
            } else {
                return "pwFail";
            }
        } else {
            return "idFail";
        }
    }
}
