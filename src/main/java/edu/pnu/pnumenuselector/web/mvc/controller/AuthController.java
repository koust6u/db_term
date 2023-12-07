package edu.pnu.pnumenuselector.web.mvc.controller;

import edu.pnu.pnumenuselector.domain.data.dto.authority.ChangeAuthForm;
import edu.pnu.pnumenuselector.domain.data.dto.authority.MemberView;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import edu.pnu.pnumenuselector.domain.data.entity.member.Role;
import edu.pnu.pnumenuselector.web.WebConstant;
import edu.pnu.pnumenuselector.web.mvc.service.AuthorityService;
import edu.pnu.pnumenuselector.web.mvc.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static edu.pnu.pnumenuselector.domain.data.entity.member.Role.ADMIN;
import static edu.pnu.pnumenuselector.web.WebConstant.SESSION_ID;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthorityService authorityService;

    private final MemberService memberService;


    @GetMapping("/member/all")
    ResponseEntity<?> searchAllMemberViews(@SessionAttribute(name = SESSION_ID) Member member){
        if (!authorityService.isValidAccess(member.getUserId(), ADMIN)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<MemberView> memberViews = authorityService.viewOfAllMembers();

        return ResponseEntity.ok(memberViews);
    }


    @PatchMapping("/member/auth")
    ResponseEntity<?> changeMemberAuth(@SessionAttribute(name = SESSION_ID) Member member,
                                       @RequestBody ChangeAuthForm form){
        if (!authorityService.isValidAccess(member.getUserId(), ADMIN)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        authorityService.ChangeAuthorityGrade(form, member.getUserId());

        return ResponseEntity.ok().build();
    }


    @GetMapping("/myAuth")
    public ResponseEntity<?> myAuth(@SessionAttribute(name = SESSION_ID)Member member){
        member = memberService.searchMemberByUserId(member.getUserId());
        Integer grade = member.getAuthority().getRole().getGrade();

        return ResponseEntity.ok(grade);
    }
}
