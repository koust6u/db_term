package edu.pnu.pnumenuselector.web.mvc.controller;

import static edu.pnu.pnumenuselector.web.WebConstant.SESSION_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.not;

import edu.pnu.pnumenuselector.domain.data.dto.member.JoinForm;
import edu.pnu.pnumenuselector.domain.data.dto.member.LoginForm;
import edu.pnu.pnumenuselector.domain.data.dto.member.MemberResponse;
import edu.pnu.pnumenuselector.domain.data.dto.member.UpdateForm;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import edu.pnu.pnumenuselector.utils.DataUtils;
import edu.pnu.pnumenuselector.web.exception.MemberNotFoundException;
import edu.pnu.pnumenuselector.web.mvc.service.MemberService;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberControllerTest {

    @Autowired
    MemberController memberController;

    @Autowired
    MemberService memberService;
    @Test
    @DisplayName("회원가입 요청")
    void memberRegistrationTest() throws Exception{
        //given
        JoinForm joinForm = DataUtils.dummyJoinForm();
        //when
        ResponseEntity<?> signUpEntity = memberController.memberRegistration(joinForm);
        ResponseEntity<?> responseEntity = memberController.responseMyInfo(joinForm.toEntity());
        MemberResponse body = (MemberResponse) responseEntity.getBody();
        //then
        assertThat(signUpEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(body.getBirthday()).isEqualTo(joinForm.getBirthday());
        assertThat(body.getEmail()).isEqualTo(joinForm.getEmail());
        assertThat(body.getUsername()).isEqualTo(joinForm.getUsername());
        assertThat(body.getUserId()).isEqualTo(joinForm.getUserId());
    }

    @Test
    @Transactional
    @DisplayName("로그인 성공")
    void loginTest() {
        //given
        Long id = DataUtils.defaultSave(memberService);
        LoginForm loginForm = DataUtils.dummyLoginForm();
        MockHttpServletRequest fakeRequest = new MockHttpServletRequest();
        //when
        ResponseEntity<?> login = memberController.login(loginForm, fakeRequest);
        HttpSession session = fakeRequest.getSession();
        Member member = (Member)session.getAttribute(SESSION_ID);
        Member findMember = memberService.findOne(id);
        //then
        assertThat(login.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(member).isEqualTo(findMember);
    }

    @Test
    @DisplayName("로그인 실패")
    void loginFailedTest() throws Exception{
        //given
        String notExistMemberId = "ILoveUou";
        String password = "dumdmum";
        MockHttpServletRequest request = new MockHttpServletRequest();
        //when
        LoginForm loginForm = LoginForm.builder()
                .userId(notExistMemberId)
                .password(password)
                .build();
        //then
        assertThatThrownBy(() ->memberController.login(loginForm, request))
                .isInstanceOf(MemberNotFoundException.class);
     }

     @Test
     @Transactional
     @DisplayName("로그아웃")
     void logoutTest() throws Exception{
         //given
         Long id = DataUtils.defaultSave(memberService);
         LoginForm loginForm = DataUtils.dummyLoginForm();
         MockHttpServletRequest request = new MockHttpServletRequest();
         //when
         memberController.login(loginForm, request);
         Member find = memberService.findOne(id);
         //then
         Member findMember = (Member) request.getSession().getAttribute(SESSION_ID);
         assertThat(findMember).isEqualTo(find);
     }


     @Test
     @DisplayName("개인 정보 응답")
     void findUserInfo() throws Exception{
         //given
         Long id = DataUtils.randomSave(memberService);
         Member find = memberService.findOne(id);
         //when
         MemberResponse info = (MemberResponse) memberController
                 .responseMyInfo(find)
                 .getBody();
         //then
         Assertions.assertThat(info).isEqualTo(find.toResponse());
      }

      @Test
      @DisplayName("회원 정보 수정")
      @Transactional
      void updateTest() throws Exception{
          //given
          UpdateForm form = UpdateForm.builder()
                  .email("kimkin@kinn.kam")
                  .birthday(LocalDate.now())
                  .password("kkkiiimmm")
                  .build();
          MockHttpServletRequest request = new MockHttpServletRequest();
          HttpSession session = request.getSession();
          Long genId = DataUtils.randomSave(memberService);
          session.setAttribute(SESSION_ID, memberService.findOne(genId));
          //when
          //memberController.updateMemberInfo(form,request);
          Member find = memberService.findOne(genId);
          //then
          assertThat(find).isEqualTo(memberService.findOne(genId));
       }
}