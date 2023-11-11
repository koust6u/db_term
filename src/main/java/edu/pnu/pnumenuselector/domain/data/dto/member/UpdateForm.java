package edu.pnu.pnumenuselector.domain.data.dto.member;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateForm {

    String userId;
    LocalDate  birthday;
    String email;
    String password;
    String modifyBy;

}
