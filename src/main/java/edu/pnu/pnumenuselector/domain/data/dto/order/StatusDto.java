package edu.pnu.pnumenuselector.domain.data.dto.order;

import edu.pnu.pnumenuselector.domain.data.entity.order.OrderStatus;
import edu.pnu.pnumenuselector.domain.data.entity.order.Receipt;
import lombok.Data;

@Data
public class StatusDto {

    String title;
    Receipt receipt;

    OrderStatus status;
}
