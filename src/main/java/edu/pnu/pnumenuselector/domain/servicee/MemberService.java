package edu.pnu.pnumenuselector.domain.servicee;

import edu.pnu.pnumenuselector.data.JoinFormDto;
import edu.pnu.pnumenuselector.domain.repository.MemberRepository;
import edu.pnu.pnumenuselector.entity.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public void signUp(Member member){
        memberRepository.save(member);
    }

    public Member findOne(Long id){
        return memberRepository.findById(id).orElseThrow();
    }

}
