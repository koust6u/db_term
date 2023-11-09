package edu.pnu.pnumenuselector.domain.service;

import edu.pnu.pnumenuselector.domain.repository.MemberRepository;
import edu.pnu.pnumenuselector.entity.member.Member;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    @Transactional
    void signUp() {
        Member member1 = Member.builder()
                .userId("user1")
                .birthDay(LocalDate.now())
                .password("pass")
                .email("aa@bb.cc")
                .build();

        memberService.signUp(member1);

        Member member2 = memberService.findOne(member1.getId());

        assertEquals(member1, member2);
    }

    @Test
    @DisplayName("계좌, 권한, 프로필 연쇄 생성")
    @Transactional
    void testAlldepnency() throws Exception{
        //given
        Member member = Member.builder()
                .userId("aa")
                .birthDay(LocalDate.now())
                .password("bb")
                .email("aa@bb.cc")
                .build();
        //when
        memberService.signUp(member);
        //then
     }
}