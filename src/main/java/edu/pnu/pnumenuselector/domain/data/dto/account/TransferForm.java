package edu.pnu.pnumenuselector.domain.data.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferForm {

    private Long credit;
}
