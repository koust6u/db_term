package edu.pnu.pnumenuselector.web.mvc.service;

import edu.pnu.pnumenuselector.domain.data.dto.account.LogForm;
import edu.pnu.pnumenuselector.domain.data.entity.account.TransactionLog;
import edu.pnu.pnumenuselector.web.mvc.repository.TransactionLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TransactionLogService {

    private final TransactionLogRepository transactionLogRepository;



    public List<LogForm> findOrigin(String userId){
        return transactionLogRepository.findTransactionLogsByFromUserId(userId)
                .stream()
                .filter(entity -> entity.getContent().equals("송금"))
                .map(entity -> entity.convertToDto("origin"))
                .sorted(Comparator.comparing(LogForm::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

    public List<LogForm> findDest(String userId){
        return transactionLogRepository.findTransactionLogsByToUserId(userId)
                .stream()
                .filter(entity -> entity.getContent().equals("착금") || entity.getContent().equals("입금"))
                .map(entity -> entity.convertToDto("destination"))
                .sorted(Comparator.comparing(LogForm::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

    public List<LogForm> findAll(String userId){
        List<LogForm> origin = findOrigin(userId);
        origin.addAll(findDest(userId));

        return origin.stream()
                .sorted(Comparator.comparing(LogForm::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }
}
