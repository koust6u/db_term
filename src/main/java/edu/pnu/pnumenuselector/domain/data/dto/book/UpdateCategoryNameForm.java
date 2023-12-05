package edu.pnu.pnumenuselector.domain.data.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCategoryNameForm {
    @NotBlank
    @NotNull
    private String name;
}
