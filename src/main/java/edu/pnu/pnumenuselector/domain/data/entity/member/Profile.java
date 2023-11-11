package edu.pnu.pnumenuselector.domain.data.entity.member;

import static jakarta.persistence.CascadeType.ALL;

import jakarta.persistence.*;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Profile {

    @Id
    @Getter
    @GeneratedValue
    @Column(name= "PROFILE_ID")
    private Long id;

    private String name;
    private String message;

    @Column(name = "URL")
    private String profilePhotoUrl;

    @Getter
    @OneToOne(orphanRemoval = true, fetch = FetchType.LAZY, cascade = ALL)
    @PrimaryKeyJoinColumn(name = "MEMBER_ID")
    private Member member;
}
