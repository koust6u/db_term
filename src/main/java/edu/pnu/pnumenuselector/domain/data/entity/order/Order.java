package edu.pnu.pnumenuselector.domain.data.entity.order;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ORDERS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;


    @ManyToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "LENDER")
    private Member lender;

    @ManyToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "BORROWER")
    private Member borrower;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime period;

    @Enumerated(EnumType.STRING)
    private Receipt receipt;
}
