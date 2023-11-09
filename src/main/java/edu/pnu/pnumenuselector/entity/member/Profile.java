package edu.pnu.pnumenuselector.entity.member;

import static jakarta.persistence.CascadeType.ALL;

import jakarta.persistence.*;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToOne(orphanRemoval = true, fetch = FetchType.LAZY, cascade = ALL)
    @PrimaryKeyJoinColumn(name = "MEMBER_ID")
    private Member member;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Profile profile = (Profile) o;
        return Objects.equals(id, profile.id) && Objects.equals(name, profile.name)
                && Objects.equals(message, profile.message) && Objects.equals(profilePhotoUrl,
                profile.profilePhotoUrl) && Objects.equals(member, profile.member);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, message, profilePhotoUrl, member);
    }
}
