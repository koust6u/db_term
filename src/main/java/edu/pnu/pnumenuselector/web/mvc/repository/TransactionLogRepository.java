package edu.pnu.pnumenuselector.web.mvc.repository;

import edu.pnu.pnumenuselector.domain.data.entity.account.TransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionLogRepository extends JpaRepository<TransactionLog,Long> {

    List<TransactionLog> findTransactionLogsByToUserId(String toUserId);

    List<TransactionLog> findTransactionLogsByFromUserId(String fromUserId);

}
