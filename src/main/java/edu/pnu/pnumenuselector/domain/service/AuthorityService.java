package edu.pnu.pnumenuselector.domain.service;

import static edu.pnu.pnumenuselector.entity.member.Role.NORMAL;

import edu.pnu.pnumenuselector.domain.repository.AuthorityRepository;
import edu.pnu.pnumenuselector.entity.member.Authority;
import edu.pnu.pnumenuselector.entity.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Transactional
    public void grantByMember(Member member){
        Authority newAuthority = Authority.builder()
                .role(NORMAL)
                .member(member)
                .build();
        authorityRepository.save(newAuthority);
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
