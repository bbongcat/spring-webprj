package com.myapp.webprj.member.controller;

import com.myapp.webprj.member.domain.Member;
import com.myapp.webprj.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Log4j2
public class MemberController {

    private final MemberService memberService;

    //회원가입 페이지 화면 열기
    @GetMapping("/member/sign-up")
    public void signUp() {
        //return "member/sign-up"; 입력 경로와 출력 경로가 같을 경우 void로 바꾸고 리턴을 없애도 결과는 똑같다.
    }

    //데이터(아이디, 이메일) 중복체크 비동기 요청처리
    // ~~~/check?kind=account&info=banana
    @GetMapping("/check")
    @ResponseBody
    public ResponseEntity<Boolean> check(String kind, String info) {

        boolean flag = memberService.isDuplicate(kind, info);

        return new ResponseEntity<>(flag, HttpStatus.OK);
    }

    //회원가입 요청 처리
    @PostMapping("/member/sign-up")
    public String signUp(Member member) {
        log.info("/member/sign-up POST! : " + member);

        memberService.signUp(member);

        return "redirect:/board/list";
    }

}
