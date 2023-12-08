package edu.pnu.pnumenuselector.domain.data.dto.profile;

import edu.pnu.pnumenuselector.domain.data.entity.member.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileDto {

    private String message;
    private String nickname;

    private String tel;
    private String url;


    public Profile toEntity(){
        return Profile.builder()
                .name(this.nickname)
                .tel(this.tel)
                .message(this.message)
                .profilePhotoUrl(this.url)
                .build();
    }
}
