package edu.pnu.pnumenuselector.domain.data.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountInfo {

    private Long credit;

    private List<LogForm> originLogs;

    private List<LogForm> destLogs;

    private List<LogForm> allLogs;

}
