package edu.pnu.pnumenuselector.web.mvc.service;

import static edu.pnu.pnumenuselector.domain.data.entity.member.Role.NORMAL;

import edu.pnu.pnumenuselector.domain.data.dto.authority.ChangeAuthForm;
import edu.pnu.pnumenuselector.domain.data.dto.authority.MemberView;
import edu.pnu.pnumenuselector.domain.data.entity.member.Role;
import edu.pnu.pnumenuselector.web.exception.MemberNotFoundException;
import edu.pnu.pnumenuselector.web.mvc.repository.AuthorityRepository;
import edu.pnu.pnumenuselector.domain.data.entity.member.Authority;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import edu.pnu.pnumenuselector.web.mvc.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    private final MemberRepository memberRepository;
    @Transactional
    public Authority grantByMember(Member member){
        Authority newAuthority = Authority.builder()
                .role(NORMAL)
                .member(member)
                .build();
        authorityRepository.save(newAuthority);
        return newAuthority;
    }


    @Transactional
    public void ChangeAuthorityGrade(ChangeAuthForm form, String manager){
        Member target = memberRepository.findByUserId(form.getTarget())
                .orElseThrow(MemberNotFoundException::new);

        Authority authority = target.getAuthority();
        authority.updateRole(form.getRole(), manager);
        authority.changeAuth(form.getBannedPeriod());
    }


    public List<MemberView> viewOfAllMembers(){
        return memberRepository.findAll()
                .stream()
                .map(Member::convertToMemberView)
                .toList();
    }


    public boolean isValidAccess(String memberId, Role required){
        Member member = memberRepository.findByUserId(memberId)
                .orElseThrow(MemberNotFoundException::new);
        Role memberRole = member.getAuthority().getRole();

        return required.getGrade() <= memberRole.getGrade();
    }

    public boolean isBanned(String userId){
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(MemberNotFoundException::new);

        LocalDateTime bannedTime = member.getAuthority().getBannedTime();

        return !(bannedTime == null || bannedTime.isBefore(LocalDateTime.now()));
    }
    @Transactional
    public void grant(Authority authority){
        authorityRepository.save(authority);
    }


    public Authority findOne(Long id){
        return authorityRepository.findById(id)
                .orElseThrow();
    }

}
