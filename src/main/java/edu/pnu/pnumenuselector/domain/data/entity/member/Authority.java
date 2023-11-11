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
@EqualsAndHashCode
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

    @Getter
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = ALL)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

}
