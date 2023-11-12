package edu.pnu.pnumenuselector.web.mvc.repository;

import edu.pnu.pnumenuselector.domain.data.entity.account.TransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionLogRepository extends JpaRepository<TransactionLog,Long> {
}
