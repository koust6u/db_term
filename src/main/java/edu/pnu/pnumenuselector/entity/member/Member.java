package edu.pnu.pnumenuselector.entity.member;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;

import edu.pnu.pnumenuselector.entity.BaseEntity;
import edu.pnu.pnumenuselector.entity.account.Account;
import jakarta.persistence.*;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import org.apache.logging.log4j.util.PropertySource.Comparator;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name="MEMBER_ID")
    @Getter
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    private String email;

    @Temporal(TemporalType.DATE)
    private LocalDate birthDay;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = ALL)
    @JoinColumn(name = "AUTHORITY_ID")
    private Authority authority;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Member member = (Member) o;
        return Objects.equals(id, member.id) && Objects.equals(userId, member.userId)
                && Objects.equals(password, member.password) && Objects.equals(email, member.email)
                && Objects.equals(birthDay, member.birthDay)
                && Objects.equals(authority, member.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, password, email, birthDay,  authority);
    }
}
