package edu.pnu.pnumenuselector.domain.service;

import edu.pnu.pnumenuselector.domain.repository.MemberRepository;
import edu.pnu.pnumenuselector.entity.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final ProfileService profileService;
    private final AccountService accountService;
    private final AuthorityService authorityService;

    @Transactional
    public void signUp(Member member) {
        memberRepository.save(member);
        accountService.createByMember(member);
        profileService.createByMember(member);
        authorityService.grantByMember(member);
        profileService.createByMember(member);
    }

    public Member findOne(Long id) {
        return memberRepository.findById(id).orElseThrow();
    }

}
