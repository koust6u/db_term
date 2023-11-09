package edu.pnu.pnumenuselector.domain.service;

import edu.pnu.pnumenuselector.domain.repository.AccountRepository;
import edu.pnu.pnumenuselector.entity.account.Account;
import edu.pnu.pnumenuselector.entity.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {
    private final AccountRepository accountRepository;

    @Transactional
    public void createByMember(Member member){
        Account newAccount = Account.builder()
                .member(member)
                .credit(0L)
                .build();

        accountRepository.save(newAccount);
    }
    @Transactional
    public void create(Account account){
        accountRepository.save(account);
    }
    public Account findOne(Long id){
        return accountRepository.findById(id).orElseThrow();
    }
}
