package edu.pnu.pnumenuselector.entity.member;

import jakarta.persistence.*;

@Entity
public class Profile {

    @Id
    @GeneratedValue
    @Column(name= "PROFILE_ID")
    private Long id;
    private String name;
    private String message;

    @Column(name = "URL")
    private String profilePhotoUrl;

    @OneToOne(orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
