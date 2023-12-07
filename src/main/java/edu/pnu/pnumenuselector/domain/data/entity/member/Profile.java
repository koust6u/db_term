package edu.pnu.pnumenuselector.domain.data.entity.member;

import static jakarta.persistence.CascadeType.ALL;

import edu.pnu.pnumenuselector.domain.data.dto.profile.ProfileDto;
import edu.pnu.pnumenuselector.domain.data.entity.listener.ProfileEntityListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
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


    @Getter
    private String tel;


    @Column(name = "URL")
    @Getter
    private String profilePhotoUrl;

    @Getter
    @OneToOne(orphanRemoval = true, fetch = FetchType.LAZY, cascade = ALL)
    @PrimaryKeyJoinColumn(name = "MEMBER_ID")
    private Member member;


    public ProfileDto toResponse(){
        return ProfileDto.builder()
                .url(this.profilePhotoUrl)
                .nickname(this.name)
                .message(this.message)
                .build();
    }

    public void update(ProfileDto response){
        this.name = response.getNickname();
        this.message = response.getMessage();
        this.profilePhotoUrl = response.getUrl();
    }

    public void init(String tel, String url,String name, String message){
        this.tel = tel;
        this.profilePhotoUrl = url;
        this.message = message;
        this.name  = name;
    }
}
