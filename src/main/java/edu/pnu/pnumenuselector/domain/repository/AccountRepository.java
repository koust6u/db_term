package edu.pnu.pnumenuselector.domain.repository;

import edu.pnu.pnumenuselector.entity.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
}
