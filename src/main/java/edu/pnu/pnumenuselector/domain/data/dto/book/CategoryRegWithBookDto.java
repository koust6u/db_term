package edu.pnu.pnumenuselector.domain.data.dto.book;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CategoryRegWithBookDto {

    private List<String> names;


}
