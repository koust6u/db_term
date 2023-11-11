package edu.pnu.pnumenuselector.domain.data.entity.member;

import static jakarta.persistence.CascadeType.ALL;

import edu.pnu.pnumenuselector.domain.data.dto.profile.ProfileResponse;
import edu.pnu.pnumenuselector.domain.data.entity.listener.ProfileEntityListener;
import jakarta.persistence.*;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@NoArgsConstructor
@EntityListeners(ProfileEntityListener.class)
@AllArgsConstructor
public class Profile {

    @Id
    @Getter
    @GeneratedValue
    @Column(name= "PROFILE_ID")
    private Long id;

    @Getter
    @Setter
    @Column(name = "NICKNAME")
    private String name;
    private String message;

    @Column(name = "URL")
    private String profilePhotoUrl;

    @Getter
    @OneToOne(orphanRemoval = true, fetch = FetchType.LAZY, cascade = ALL)
    @PrimaryKeyJoinColumn(name = "MEMBER_ID")
    private Member member;


    public ProfileResponse toResponse(){
        return ProfileResponse.builder()
                .url(this.profilePhotoUrl)
                .nickname(this.name)
                .message(this.message)
                .build();
    }
}
