package edu.pnu.pnumenuselector.domain.data.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private String bookName;
    private String ownerId;

    private Long price;
}
