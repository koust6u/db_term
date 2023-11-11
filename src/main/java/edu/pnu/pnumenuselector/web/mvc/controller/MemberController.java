package edu.pnu.pnumenuselector.web.mvc.controller;

import static edu.pnu.pnumenuselector.web.WebConstant.SESSION_ID;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import edu.pnu.pnumenuselector.domain.data.dto.JoinForm;
import edu.pnu.pnumenuselector.domain.data.dto.LoginForm;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import edu.pnu.pnumenuselector.web.WebConstant;
import edu.pnu.pnumenuselector.web.mvc.service.MemberService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;


    @PostMapping("/signup")
    public ResponseEntity<?> memberRegistration(@RequestBody JoinForm form){
        memberService.signUp(form.toEntity());
        return ResponseEntity.status(CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(LoginForm form,HttpServletRequest request){
        Member certifiedMember = memberService.login(form.getUserId(), form.getPassword());

        HttpSession session = request.getSession();
        session.setAttribute(SESSION_ID, certifiedMember);

        return ResponseEntity.status(OK).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null){
            session.invalidate();
        }
        return ResponseEntity.status(OK).build();
    }
    @DeleteMapping
    public ResponseEntity<?> memberRemoval(
            HttpServletRequest request,
            @SessionAttribute(name = SESSION_ID, required = false)Member member
    ){
        memberService.withdrawal(member.getUserId());
        HttpSession session = request.getSession(false);
        session.invalidate();
        return ResponseEntity.status(OK).build();
    }

}
