package edu.pnu.pnumenuselector.web.mvc.controller;

import static edu.pnu.pnumenuselector.web.WebConstant.REMOTE_ADDR;
import static edu.pnu.pnumenuselector.web.WebConstant.SESSION_ID;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import edu.pnu.pnumenuselector.domain.data.dto.member.JoinForm;
import edu.pnu.pnumenuselector.domain.data.dto.member.LoginForm;
import edu.pnu.pnumenuselector.domain.data.dto.member.UpdateForm;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import edu.pnu.pnumenuselector.web.WebConstant;
import edu.pnu.pnumenuselector.web.mvc.service.AuthorityService;
import edu.pnu.pnumenuselector.web.mvc.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.time.LocalDateTime;


@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    private final AuthorityService authorityService;

    @GetMapping("/health")
    public ResponseEntity<?> loginCheck(@SessionAttribute(name = SESSION_ID, required = false) Member member
    ) {
        return member != null ? ResponseEntity.ok().build() : ResponseEntity.status(FORBIDDEN).build();
    }

    @GetMapping
    public ResponseEntity<?> responseMyInfo(@SessionAttribute(name = SESSION_ID, required = false) Member member) {
        return ResponseEntity.ok(member.toResponse());
    }

    @PostMapping("/signup")
    public ResponseEntity<?> memberRegistration(@RequestBody JoinForm form) {
        memberService.signUp(form);
        return ResponseEntity.status(CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm form, HttpServletRequest request) {
        Member certifiedMember = memberService.login(form.getUserId(), form.getPassword());
        if (authorityService.isBanned(form.getUserId())){
            return ResponseEntity.badRequest().body(certifiedMember.getAuthority().getBannedTime());
        }
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_ID, certifiedMember);
        session.setMaxInactiveInterval(1800);
        return ResponseEntity.status(OK).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.status(NO_CONTENT).build();
    }

    @DeleteMapping
    public ResponseEntity<?> memberRemoval(
            HttpServletRequest request,
            @SessionAttribute(name = SESSION_ID, required = false) Member member
    ) {
        log.info("{} will deleted few mins later", member.getUserId());
        memberService.withdrawal(member.getUserId());
        HttpSession session = request.getSession(false);
        session.invalidate();
        return ResponseEntity.status(NO_CONTENT).build();
    }

    @PatchMapping
    public ResponseEntity<?> updateMemberInfo(@RequestBody UpdateForm form,
                                              @SessionAttribute(name = SESSION_ID) Member member,
                                              HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String ip = request.getHeader(REMOTE_ADDR);
        form.setModifyBy(ip);
        Member update = memberService.update(form, member.getId());
        session.setAttribute(SESSION_ID, update);
        return ResponseEntity.ok(update.toResponse());
    }

    @GetMapping("/count")
    public ResponseEntity<?> count(){
        return ResponseEntity.ok(memberService.count());
    }


}
