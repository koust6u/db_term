package edu.pnu.pnumenuselector.domain.data.dto.authority;

import edu.pnu.pnumenuselector.domain.data.entity.member.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberView {

    String name;
    String userId;
    String tel;

    Role role;
    String url;
    LocalDateTime signUpTime;
    LocalDateTime bannedTime;
    LocalDateTime pardonTime;
    int bookCount;
}
