package edu.pnu.pnumenuselector.web.mvc.service;

import edu.pnu.pnumenuselector.web.mvc.repository.AccountRepository;
import edu.pnu.pnumenuselector.domain.data.entity.account.Account;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {
    private final AccountRepository accountRepository;

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
    public Account findOne(Long id){
        return accountRepository.findById(id).orElseThrow();
    }
}
