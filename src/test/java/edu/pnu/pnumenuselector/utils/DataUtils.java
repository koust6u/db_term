package edu.pnu.pnumenuselector.utils;

import edu.pnu.pnumenuselector.domain.data.dto.JoinForm;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import java.time.LocalDate;

public class DataUtils {

    public static Member dummyMember1(){
        JoinForm form  = JoinForm.builder()
                .name("name")
                .birthday(LocalDate.now())
                .email("aa@bb.cc")
                .password("bb")
                .userId("abc")
                .build();
        return form.toEntity();
    }

    public static Member dummyMember2(){
        JoinForm form  = JoinForm.builder()
                .name("name2")
                .birthday(LocalDate.now())
                .email("aa2@bb.cc")
                .password("bb2")
                .userId("abc2")
                .build();
        return form.toEntity();
    }
}
