package edu.pnu.pnumenuselector.domain.data.entity.order;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

import edu.pnu.pnumenuselector.domain.data.dto.order.OrderDto;
import edu.pnu.pnumenuselector.domain.data.entity.book.Book;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import lombok.*;

@Entity
@Table(name = "ORDERS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id @GeneratedValue
    @Getter
    @Column(name = "ORDER_ID")
    private Long id;


    @Getter
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "BOOK_ID", nullable = false)
    private Book book;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "LENDER")
    private Member lender;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "BORROWER")
    private Member borrower;

    @Setter
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime period;

    @Setter
    @Enumerated(EnumType.STRING)
    private Receipt receipt;

    public OrderDto convertToDto(){
        return OrderDto.builder()
                .lender(lender.getUserId())
                .status(this.status)
                .period(this.period)
                .receipt(this.receipt)
                .build();
    }

}
