package edu.pnu.pnumenuselector.web.mvc.service;

import edu.pnu.pnumenuselector.domain.data.dto.account.AccountResponse;
import edu.pnu.pnumenuselector.domain.data.dto.account.DepositForm;
import edu.pnu.pnumenuselector.domain.data.dto.account.TransferForm;
import edu.pnu.pnumenuselector.domain.data.entity.account.TransactionLog;
import edu.pnu.pnumenuselector.web.exception.BalanceInsufficientException;
import edu.pnu.pnumenuselector.web.exception.MemberNotFoundException;
import edu.pnu.pnumenuselector.web.mvc.repository.AccountRepository;
import edu.pnu.pnumenuselector.domain.data.entity.account.Account;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import edu.pnu.pnumenuselector.web.mvc.repository.MemberRepository;
import edu.pnu.pnumenuselector.web.mvc.repository.TransactionLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {
    private final AccountRepository accountRepository;

    private final TransactionLogRepository transactionLogRepository;
    private final MemberRepository memberRepository;

    public Account findOne(Long id){
        return accountRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Account createByMember(Member member){
        Account newAccount = Account.builder()
                .credit(0L)
                .member(member)
                .build();

        accountRepository.save(newAccount);
        return newAccount;
    }
    @Transactional
    public void create(Account account){
        accountRepository.save(account);
    }

    @Transactional
    public AccountResponse deposit(Long memberPk, DepositForm form){
        validateNegativeValue(form);

        Member member = findMember(memberPk);
        Account account = member.getAccount();
        TransactionLog deposit = depositLogging(form, member, account);
        account.changeCredit(form.getCredit());
        transactionLogRepository.save(deposit);
        return generateDepositResponse(form, member, account);
    }

    @Transactional
    public void transfer(Member fromUser, Member toUser,TransferForm form){
        Account fromAccount = fromUser.getAccount();
        Account toAccount = toUser.getAccount();
        if (fromAccount.getCredit() - form.getCredit() < 0){
            throw new BalanceInsufficientException();
        }
        remittanceLogging(fromUser, toUser, form, fromAccount);
        refundLogging(fromUser, toUser, form, toAccount);

        fromAccount.changeCredit( -1 * form.getCredit());
        toAccount.changeCredit(form.getCredit());
    }

    private void refundLogging(Member fromUser, Member toUser, TransferForm form, Account toAccount) {
        TransactionLog refund = TransactionLog.builder()
                .account(toAccount)
                .fromUserId(fromUser.getUserId())
                .toUserId(toUser.getUserId())
                .credit(form.getCredit())
                .totalCredit(toAccount.getCredit() + form.getCredit())
                .content("착금")
                .build();
        transactionLogRepository.save(refund);
    }

    private void remittanceLogging(Member fromUser, Member toUser, TransferForm form, Account fromAccount) {
        TransactionLog remittance = TransactionLog.builder()
                .fromUserId(fromUser.getUserId())
                .toUserId(toUser.getUserId())
                .content("송금")
                .credit(-1 * form.getCredit())
                .totalCredit(fromAccount.getCredit() - form.getCredit())
                .account(fromAccount)
                .build();
        transactionLogRepository.save(remittance);
    }

    private TransactionLog depositLogging(DepositForm form, Member member, Account account) {
        return TransactionLog.builder()
                .content("입금")
                .fromUserId("입금 결제 시스템")
                .toUserId(member.getUserId())
                .credit(form.getCredit())
                .account(account)
                .totalCredit(account.getCredit() + form.getCredit())
                .build();
    }

    private AccountResponse generateDepositResponse(DepositForm form, Member member, Account account) {

        return AccountResponse.builder()
                .userId(member.getUserId())
                .credit(form.getCredit())
                .totalCredit(account.getCredit())
                .build();
    }


    private Member findMember(Long memberPk) {
        return memberRepository.findById(memberPk)
                .orElseThrow(MemberNotFoundException::new);
    }


    private void validateNegativeValue(DepositForm form){
        if (form.getCredit() < 0){
            throw new IllegalArgumentException("입금은 0크레딧 이상 부터 가능합니다.");
        }
    }
}
