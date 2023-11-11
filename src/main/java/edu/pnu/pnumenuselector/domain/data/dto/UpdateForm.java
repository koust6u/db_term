package edu.pnu.pnumenuselector.domain.data.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateForm {

    String userId;

    LocalDate  birthday;
    String email;
    String password;

}
