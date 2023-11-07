package edu.pnu.pnumenuselector.domain.servicee;

import edu.pnu.pnumenuselector.domain.repository.MemberRepository;
import edu.pnu.pnumenuselector.entity.member.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    EntityManager em;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void signUp() {
        Member member1 = Member.builder()
                .userId("user1")
                .birthDay(LocalDate.now())
                .password("pass")
                .email("aa@bb.cc")
                .build();

        memberService.signUp(member1);

        Member member2 = memberService.findOne(member1.getId());

        assertThat(member1).isEqualTo(member2);
    }
}