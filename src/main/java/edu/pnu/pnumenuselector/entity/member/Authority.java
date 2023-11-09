package edu.pnu.pnumenuselector.entity.member;

import static jakarta.persistence.CascadeType.ALL;

import edu.pnu.pnumenuselector.entity.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authority extends BaseEntity {

    @Id
    @GeneratedValue
    @Getter
    @Column(name = "AUTHORITY_ID")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime bannedTime;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime pardonTime;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = ALL)
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
        Authority authority = (Authority) o;
        return Objects.equals(id, authority.id) && Objects.equals(bannedTime, authority.bannedTime)
                && Objects.equals(pardonTime, authority.pardonTime) && role == authority.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bannedTime, pardonTime, role);
    }
}
