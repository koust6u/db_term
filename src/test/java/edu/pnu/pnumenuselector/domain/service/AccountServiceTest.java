package edu.pnu.pnumenuselector.domain.service;

import static org.junit.jupiter.api.Assertions.*;

import edu.pnu.pnumenuselector.domain.repository.AccountRepository;
import edu.pnu.pnumenuselector.entity.account.Account;
import edu.pnu.pnumenuselector.entity.member.Member;
import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Rollback(false)
class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    MemberService memberService;

    @Test
    @Transactional
    @DisplayName("계좌 생성")
    void entityTest() throws Exception {
        //given
        Member member1 = Member.builder()
                .email("aa@bb.cc")
                .birthDay(LocalDate.now())
                .password("aa")
                .userId("abc")
                .build();

        Account account1 = Account.builder()
                .member(member1)
                .credit(0L)
                .build();
        //when
        memberService.signUp(member1);
        accountService.create(account1);
        //then
        Account findAccount = accountService.findOne(account1.getId());

        Assertions.assertThat(account1).isEqualTo(findAccount);
    }
}