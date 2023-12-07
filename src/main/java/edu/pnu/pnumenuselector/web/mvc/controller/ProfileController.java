package edu.pnu.pnumenuselector.web.mvc.controller;

import static edu.pnu.pnumenuselector.web.WebConstant.SESSION_ID;

import edu.pnu.pnumenuselector.domain.data.dto.profile.ProfileDto;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import edu.pnu.pnumenuselector.domain.data.entity.member.Profile;
import edu.pnu.pnumenuselector.web.WebConstant;
import edu.pnu.pnumenuselector.web.mvc.service.ProfileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@RestController
@RequestMapping("/member/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<?> showVisibleMemberInfo(@SessionAttribute(name = SESSION_ID)Member member){
        Profile profile = profileService.findOne(member.getId());
        return ResponseEntity.ok(profile.toResponse());
    }

    @PatchMapping
    public ResponseEntity<?> updateVisibleMemberInfo(@SessionAttribute(name = SESSION_ID)Member member,
                                                     @RequestBody ProfileDto dto){
        ProfileDto update = profileService.update(dto, member);
        return ResponseEntity.ok(update);
    }
}
