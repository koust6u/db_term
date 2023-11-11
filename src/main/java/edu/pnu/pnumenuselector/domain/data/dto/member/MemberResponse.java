package edu.pnu.pnumenuselector.domain.data.dto.member;

import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import jakarta.persistence.Entity;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class MemberResponse {

    private String userId;
    private String username;
    private LocalDate birthday;
    private String email;

}
