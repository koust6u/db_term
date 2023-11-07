package edu.pnu.pnumenuselector.data;

import edu.pnu.pnumenuselector.entity.member.Member;
import lombok.Data;

import java.time.LocalDate;

@Data
public class JoinFormDto {
    private String name;
    private String userId;
    private String password;
    private String email;
    private LocalDate birthday;

    public Member toEntity(){
        return Member.builder()
                .birthDay(this.birthday)
                .userId(this.userId)
                .password(this.password)
                .build();
    }
}
