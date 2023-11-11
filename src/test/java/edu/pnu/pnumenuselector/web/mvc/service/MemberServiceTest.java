package edu.pnu.pnumenuselector.web.mvc.service;

import edu.pnu.pnumenuselector.domain.data.dto.JoinForm;
import edu.pnu.pnumenuselector.domain.data.entity.account.Account;
import edu.pnu.pnumenuselector.domain.data.entity.member.Authority;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import edu.pnu.pnumenuselector.domain.data.entity.member.Profile;
import edu.pnu.pnumenuselector.utils.DataUtils;
import edu.pnu.pnumenuselector.web.exception.DuplicateUserIdException;
import edu.pnu.pnumenuselector.web.exception.MemberNotFoundException;
import edu.pnu.pnumenuselector.web.exception.WrongPasswordException;
import edu.pnu.pnumenuselector.web.mvc.service.MemberService;
import javax.xml.crypto.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    @Transactional
    void signUp() {

        Member member = DataUtils.dummyMember1();

        Long id = memberService.signUp(member);

        Member member2 = memberService.findOne(id);

        assertThat(member).isEqualTo(member2);
    }

    @Test
    @DisplayName("계좌, 권한, 프로필 연쇄 생성")
    @Transactional
    void testAllDependency() throws Exception {
        //given
        Member member = DataUtils.dummyMember1();
        //when
        Long id = memberService.signUp(member);
        Member findMember = memberService.findOne(id);
        Account findAccount = findMember.getAccount();
        Authority findAuthority = findMember.getAuthority();
        Profile findProfile = findMember.getProfile();
        //then
        assertThat(findMember).isEqualTo(findAuthority.getMember());
        assertThat(findMember).isEqualTo(findAccount.getMember());
        assertThat(findMember).isEqualTo(findProfile.getMember());
    }


    @Test
    @Transactional
    @DisplayName("아이디 중복시 DuplicateUserIdException 발생")
    void duplicateTest() throws Exception {
        //given
        Member member = DataUtils.dummyMember1();
        //when
        memberService.signUp(member);
        //then
        assertThatThrownBy(() -> memberService.signUp(member))
                .isInstanceOf(DuplicateUserIdException.class);
    }


    @Test
    @DisplayName("로그인 성공")
    @Transactional
    void loginTest() throws Exception {
        //given
        Member member = DataUtils.dummyMember1();
        //when
        memberService.signUp(member);
        //then
        memberService.login(member.getUserId(), member.getPassword());
    }


    @Test
    @DisplayName("일치 아이디 없을 경우")
    void loginInvalidIdTest() throws Exception {
        //given
        Member member = DataUtils.dummyMember1();
        //when
        memberService.signUp(member);
        //then
        assertThatThrownBy(() -> memberService.login("iam신뢰에요", member.getPassword()))
                .isInstanceOf(MemberNotFoundException.class);
    }

    @Test
    @DisplayName("비밀번호 틀렸을 시")
    void loginInvalidPWTest() throws Exception {
        //given
        Member member = DataUtils.dummyMember2();
        //when
        memberService.signUp(member);
        //then
        assertThatThrownBy(() -> memberService.login(member.getUserId(), "iam신뢰에요."))
                .isInstanceOf(WrongPasswordException.class);
    }
}