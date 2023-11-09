package edu.pnu.pnumenuselector.domain.service;

import static edu.pnu.pnumenuselector.entity.member.Role.ADMIN;
import static edu.pnu.pnumenuselector.entity.member.Role.ANONYMOUS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import edu.pnu.pnumenuselector.entity.member.Authority;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthorityServiceTest {

    @Autowired
    AuthorityService authorityService;

    @Test
    @DisplayName("초기 권한 생성")
    void createAuth() throws Exception{
        //given
        Authority initAuth = Authority.builder()
                .role(ANONYMOUS)
                .build();
        Authority bannedAuth = Authority.builder()
                .role(ADMIN)
                .bannedTime(LocalDateTime.now())
                .build();
        //when
        authorityService.grant(initAuth);
        authorityService.grant(bannedAuth);

        Authority one = authorityService.findOne(initAuth.getId());
        Authority two = authorityService.findOne(bannedAuth.getId());
        //then
        assertEquals(initAuth, one);
     }

}