package edu.pnu.pnumenuselector.web.mvc.service;

import static org.assertj.core.api.Assertions.assertThat;

import edu.pnu.pnumenuselector.domain.data.dto.account.DepositForm;
import edu.pnu.pnumenuselector.domain.data.dto.account.TransferForm;
import edu.pnu.pnumenuselector.domain.data.dto.member.JoinForm;
import edu.pnu.pnumenuselector.domain.data.entity.account.Account;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import edu.pnu.pnumenuselector.utils.DataUtils;
import edu.pnu.pnumenuselector.web.exception.BalanceInsufficientException;
import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
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
        assertThat(account1).isEqualTo(findAccount);
    }

    @Test
    @Transactional
    @DisplayName("계좌 충전")
    void depositTest() throws Exception{
        //given
        Long id = DataUtils.randomSave(memberService);
        //when
        accountService.deposit(id, DepositForm.builder()
                .credit(100L)
                .build());
        Account account= memberService.findOne(id).getAccount();
        //then
        assertThat(account.getCredit()).isEqualTo(100L);
    }


    @Test
    @DisplayName("잔액 부족")
    @Transactional
    void invalidBalanceTest() throws Exception{
        //given
        Long from = DataUtils.randomSave(memberService);
        Long to = DataUtils.randomSave(memberService);
        TransferForm form = TransferForm.builder()
                .amount(1000L)
                .build();

        Member fromMember = memberService.findOne(from);
        Member toMember = memberService.findOne(to);
        //then
        Assertions.assertThatThrownBy(()-> accountService.transfer(fromMember, toMember,form))
                .isInstanceOf(BalanceInsufficientException.class);
     }

     @Test
     @Rollback(false)
     @DisplayName("정상 입금")
     void validTransferTest() throws Exception{
         //given
         Long from = DataUtils.randomSave(memberService);
         Long to = DataUtils.randomSave(memberService);
         TransferForm form = TransferForm.builder()
                 .amount(1000L)
                 .build();

         Member fromMember = memberService.findOne(from);
         Account fromAccount = fromMember.getAccount();
         fromAccount.changeCredit(1000L);
         Member toMember = memberService.findOne(to);
         Account toAccount = toMember.getAccount();
         //when
         accountService.transfer(fromMember, toMember, form);
         //then
         assertThat(fromAccount.getCredit()).isEqualTo(0L);
         assertThat(toAccount.getCredit()).isEqualTo(1000L);
      }
}