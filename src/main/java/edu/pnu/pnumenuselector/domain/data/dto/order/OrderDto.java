package edu.pnu.pnumenuselector.domain.data.dto.order;

import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import edu.pnu.pnumenuselector.domain.data.entity.order.Order;
import edu.pnu.pnumenuselector.domain.data.entity.order.OrderStatus;
import edu.pnu.pnumenuselector.domain.data.entity.order.Receipt;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private String lender;

    private String borrower;

    private OrderStatus status;

    private LocalDateTime period;

    private Receipt receipt;


    public Order toEntity(Member lender){
        return Order.builder()
                .lender(lender)
                .status(OrderStatus.READY)
                .period(this.period)
                .receipt(this.receipt)
                .build();
    }
}
