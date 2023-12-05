package edu.pnu.pnumenuselector.web.mvc.service;

import edu.pnu.pnumenuselector.domain.data.dto.member.UpdateForm;
import edu.pnu.pnumenuselector.web.exception.MemberNotFoundException;
import edu.pnu.pnumenuselector.web.mvc.repository.MemberRepository;
import edu.pnu.pnumenuselector.domain.data.entity.account.Account;
import edu.pnu.pnumenuselector.domain.data.entity.member.Authority;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import edu.pnu.pnumenuselector.domain.data.entity.member.Profile;
import edu.pnu.pnumenuselector.web.exception.DuplicateUserIdException;
import java.util.Optional;
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
    public Long signUp(Member member) {
        validateDuplicateUserId(member.getUserId());
        memberRepository.save(member);
        postRegistrationProcess(member);
        return member.getId();
    }

    public Member login(String userId, String password){
        Member findMember = memberRepository.findByUserId(userId)
                .orElseThrow(()-> new MemberNotFoundException(userId));
        findMember.matchPassword(password);
        return findMember;
    }

    @Transactional
    public void withdrawal(String userId){
        Member targetMember = memberRepository.findByUserId(userId)
                .orElseThrow(()-> new MemberNotFoundException(userId));
        memberRepository.delete(targetMember);
    }

    @Transactional
    public Member update(UpdateForm form,Long id){
        Member member = memberRepository.findById(id)
                .orElseThrow(MemberNotFoundException::new);
        member.updateInfo(form);
        return findOne(id);
    }
    public Member findOne(Long id) {return memberRepository.findById(id).orElseThrow();}


    public Member searchMemberByUserId(String userId){

        return memberRepository.findByUserId(userId)
                .orElseThrow(MemberNotFoundException::new);
    }
    private void postRegistrationProcess(Member member) {
        Account newAccount = accountService.createByMember(member);
        Profile newProfile = profileService.createByMember(member);
        Authority newAuthority = authorityService.grantByMember(member);
        member.initializeRelation(newAccount, newProfile, newAuthority);
    }

    private void validateDuplicateUserId(String userId){
        Optional<Member> find = memberRepository.findByUserId(userId);
        if (find.isPresent()){
            throw new DuplicateUserIdException();
        }
    }
}
