package edu.pnu.pnumenuselector.domain.data.dto.authority;

import edu.pnu.pnumenuselector.domain.data.entity.member.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ChangeAuthForm {
    private Role role;
    private int bannedPeriod;
    private String target;
}
