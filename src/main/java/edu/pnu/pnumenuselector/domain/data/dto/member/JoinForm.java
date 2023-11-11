package edu.pnu.pnumenuselector.domain.data.dto.member;

import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JoinForm {
/*
    @NotBlank(message = "이름은 비워둘 수 없습니다.")
    @Pattern(regexp = "^[가-힣]$\n", message = "이름은 한글로 입력해주세요.")
    @Min(value = 2, message = "이름은 성을 포함해 최소 2글자입니다.")
    @Max(value = 15, message = "이름은 성을 포함하여 최대 15자 까지만 허용합니다.")*/
    private String username;

    private String userId;
    private String password;
    private String email;
    private LocalDate birthday;

    public Member toEntity(){
        return Member.builder()
                .birthDay(this.birthday)
                .userId(this.userId)
                .email(this.email)
                .password(this.password)
                .username(this.username)
                .build();
    }
}
