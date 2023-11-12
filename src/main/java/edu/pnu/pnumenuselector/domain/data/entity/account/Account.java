package edu.pnu.pnumenuselector.domain.data.entity.account;

import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.transaction.annotation.Transactional;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Account {
    @Id @GeneratedValue
    @Column(name ="ACCOUNT_ID")
    private Long id;

    @Column(nullable = false)
    private Long credit;
    @CreatedDate
    private LocalDateTime createdAt;


    @OneToOne(orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;


    public void changeCredit(Long value){
        if (credit + value < 0){
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }
        credit += value;
    }
}
