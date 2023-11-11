package edu.pnu.pnumenuselector.domain.data.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginForm {

    private String userId;

    private String password;

}
