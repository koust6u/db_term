package edu.pnu.pnumenuselector.domain.data.entity.member;

import static jakarta.persistence.CascadeType.ALL;

import edu.pnu.pnumenuselector.domain.data.entity.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Authority extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "AUTHORITY_ID")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime bannedTime;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime pardonTime;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;


    public void updateRole(Role role, String manager){
        this.role = role;
        this.lastModifiedBy = manager;
    }

    public void changeAuth(int period){
        this.pardonTime = LocalDateTime.now();
        this.bannedTime = LocalDateTime.now().plusDays(period);
    }
}
