package edu.pnu.pnumenuselector.domain.data.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogForm {
    private String type;
    private Long amount;
    private LocalDateTime createdAt;
    private String to;
    private String from;
    private Long totalAmount;
}
