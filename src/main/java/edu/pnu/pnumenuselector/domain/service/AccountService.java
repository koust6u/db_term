package edu.pnu.pnumenuselector.domain.service;

import edu.pnu.pnumenuselector.domain.repository.AccountRepository;
import edu.pnu.pnumenuselector.entity.account.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {
    private final AccountRepository accountRepository;

    public void create(Account account){
        accountRepository.save(account);
    }
    public Account findOne(Long id){
        return accountRepository.findById(id).orElseThrow();
    }
}
