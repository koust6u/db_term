package edu.pnu.pnumenuselector.web.mvc.service;

import edu.pnu.pnumenuselector.domain.data.dto.JoinForm;
import edu.pnu.pnumenuselector.domain.data.entity.account.Account;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import edu.pnu.pnumenuselector.web.mvc.service.AccountService;
import edu.pnu.pnumenuselector.web.mvc.service.MemberService;
import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
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

        JoinForm form  = JoinForm.builder()
                .username("name")
                .birthday(LocalDate.now())
                .email("aa@bb.cc")
                .userId("userId")
                .build();

        Member member1 = form.toEntity();
        Account account1 = Account.builder()
                .member(member1)
                .credit(0L)
                .build();
        //when
        memberService.signUp(member1);
        accountService.create(account1);
        Account findAccount = accountService.findOne(account1.getId());
        //then
        Assertions.assertThat(account1).isEqualTo(findAccount);
    }
}