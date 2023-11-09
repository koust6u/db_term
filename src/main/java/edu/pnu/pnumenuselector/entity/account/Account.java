package edu.pnu.pnumenuselector.entity.account;

import edu.pnu.pnumenuselector.entity.member.Member;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
    @Id @GeneratedValue
    @Getter
    @Column(name ="ACCOUNT_ID")
    private Long id;

    @Column(nullable = false)
    private Long credit;
    @CreationTimestamp
    private LocalDateTime createdAt;


    @OneToOne(orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(credit, account.credit)
                && Objects.equals(createdAt, account.createdAt) && Objects.equals(member,
                account.member);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, credit, createdAt, member);
    }

}
