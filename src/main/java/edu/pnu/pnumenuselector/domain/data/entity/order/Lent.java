package edu.pnu.pnumenuselector.domain.data.entity.order;

import edu.pnu.pnumenuselector.domain.data.entity.book.Book;
import edu.pnu.pnumenuselector.domain.data.entity.order.Receipt;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Lent {

    @Id @GeneratedValue
    @Column(name = "SALE_ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID", nullable = false)
    private Book book;

    @Column(nullable = false)
    private LocalDateTime possibleLentDuration;
    @Column(nullable = false)
    private LocalDateTime periodPerLent;

    @Column(nullable = false)
    private Long credit;

    @Enumerated(EnumType.STRING)
    private Receipt receiptMethod;
}
