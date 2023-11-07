package edu.pnu.pnumenuselector.entity.member;

import edu.pnu.pnumenuselector.entity.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
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

}
