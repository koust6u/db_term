package edu.pnu.pnumenuselector.utils;

import edu.pnu.pnumenuselector.domain.data.dto.JoinForm;
import edu.pnu.pnumenuselector.domain.data.dto.LoginForm;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import edu.pnu.pnumenuselector.web.mvc.service.MemberService;
import java.time.LocalDate;
import java.util.UUID;

public class DataUtils {

    public static Long defaultSave(MemberService memberService){
        return memberService.signUp(dummyJoinForm().toEntity());
    }


    public static Long randomSave(MemberService memberService){
        return memberService.signUp(randomMember());
    }



    public static Member randomMember(){
        return Member.builder()
                .username("aa")
                .birthDay(LocalDate.now())
                .password("bb")
                .userId(UUID.randomUUID().toString())
                .email("aaa@bbbb.cccc")
                .build();
    }

    public static LoginForm dummyLoginForm(){
        return LoginForm.builder()
                .userId("dummyDummy")
                .password("dummyDummy")
                .build();
    }
    public static JoinForm dummyJoinForm(){
        return JoinForm.builder()
                .username("dummy:)")
                .userId("dummyDummy")
                .password("dummyDummy")
                .birthday(LocalDate.now())
                .email("dummy@dummy.com")
                .build();
    }

    public static Member dummyMember1(){
        JoinForm form  = JoinForm.builder()
                .username("name")
                .birthday(LocalDate.now())
                .email("aa@bb.cc")
                .password("bb")
                .userId("abc")
                .build();
        return form.toEntity();
    }

    public static Member dummyMember2(){
        JoinForm form  = JoinForm.builder()
                .username("name2")
                .birthday(LocalDate.now())
                .email("aa2@bb.cc")
                .password("bb2")
                .userId("abc2")
                .build();
        return form.toEntity();
    }
}
