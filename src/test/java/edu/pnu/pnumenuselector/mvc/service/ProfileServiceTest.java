package edu.pnu.pnumenuselector.mvc.service;

import static edu.pnu.pnumenuselector.domain.data.entity.member.Role.ADMIN;
import static org.junit.jupiter.api.Assertions.*;

import edu.pnu.pnumenuselector.domain.data.entity.member.Authority;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import edu.pnu.pnumenuselector.domain.data.entity.member.Profile;
import edu.pnu.pnumenuselector.web.mvc.service.ProfileService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class ProfileServiceTest {

    @Autowired
    ProfileService profileService;

    @Test
    @Transactional
    @DisplayName("프로필 생성")
    void createProfileTest() throws Exception{
        //given
        Authority authority = Authority.builder()
                .role(ADMIN)
                .bannedTime(LocalDateTime.now())
                .pardonTime(LocalDateTime.now())
                .build();
        Member member = Member.builder()
                .email("aa@bb.cc")
                .userId("aa")
                .password("bb")
                .birthDay(LocalDate.now())
                .build();
        Profile profile = Profile.builder()
                .profilePhotoUrl("https://www.github.com/koust6u")
                .message("안녕하세요 반갑습니다.")
                .name("koust6u")
                .member(member)
                .build();
        //when
        profileService.create(profile);
        Profile findProfile = profileService.findOne(profile.getId());
        //then
        assertEquals(profile, findProfile);
     }
}