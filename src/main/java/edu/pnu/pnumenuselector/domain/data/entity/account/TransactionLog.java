package edu.pnu.pnumenuselector.domain.data.entity.account;

import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@EntityListeners({AuditingEntityListener.class})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionLog {

    @Id
    @GeneratedValue
    @Column(name = "TRANSACTION_ID")
    private Long id;

    private String content;

    private Long credit;

    private Long totalCredit;

    @CreationTimestamp
    private LocalDateTime createdAt;


    private String toUserId;

    private String fromUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ACCOUNT_ID")
    private Account account;
}
