package edu.pnu.pnumenuselector.entity.member;

import edu.pnu.pnumenuselector.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    private String email;

    @Temporal(TemporalType.DATE)
    private LocalDate birthDay;

    @OneToOne(mappedBy = "member")
    private Profile profile;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "AUTHORITY_ID")
    private Authority authority;

}
