package edu.pnu.pnumenuselector.domain.data.dto.book;

import edu.pnu.pnumenuselector.domain.data.entity.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookLentDto {

    private String title;
    private String owner;
    private LocalDateTime period;
    private String img;
    private String desc;
    private String author;

    //READY, DELIVER, COMPLETE, PAYMENT_WAIT, NON_PAYMENT, OVERDUE
    private OrderStatus status;
}
