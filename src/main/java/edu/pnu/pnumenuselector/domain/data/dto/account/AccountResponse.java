package edu.pnu.pnumenuselector.domain.data.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

    private Long credit;
    private String userId;
    private Long totalCredit;
}
