package edu.pnu.pnumenuselector.web.mvc.controller;

import static edu.pnu.pnumenuselector.web.WebConstant.SESSION_ID;

import edu.pnu.pnumenuselector.domain.data.dto.account.AccountInfo;
import edu.pnu.pnumenuselector.domain.data.dto.account.AccountResponse;
import edu.pnu.pnumenuselector.domain.data.dto.account.DepositForm;
import edu.pnu.pnumenuselector.domain.data.dto.account.TransferForm;
import edu.pnu.pnumenuselector.domain.data.entity.account.Account;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import edu.pnu.pnumenuselector.web.WebConstant;
import edu.pnu.pnumenuselector.web.mvc.service.AccountService;
import edu.pnu.pnumenuselector.web.mvc.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    private final MemberService memberService;
    @PostMapping("/deposit")
    public ResponseEntity<?> chargeCredit(@SessionAttribute(SESSION_ID) Member member, @RequestBody DepositForm form){
        AccountResponse depositResponse = accountService.deposit(member.getId(), form);
        return ResponseEntity.ok(depositResponse);
    }

    @PostMapping("/transfer/{to-member-id}")
    public ResponseEntity<?> transfer(@SessionAttribute(SESSION_ID)Member from,
                                      @PathVariable("to-member-id") Long id,
                                      @RequestBody TransferForm form){
        Member toMember = memberService.findOne(id);
        accountService.transfer(from,toMember, form);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> accountInfo(@SessionAttribute(SESSION_ID)Member member){
        Account account = memberService.findOne(member.getId()).getAccount();
        Long credit = account.getCredit();
        AccountInfo info = AccountInfo.builder()
                .credit(credit)
                .build();
        return ResponseEntity.ok(info);
    }
}
