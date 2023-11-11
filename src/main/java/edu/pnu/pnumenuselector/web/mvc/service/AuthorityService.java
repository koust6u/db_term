package edu.pnu.pnumenuselector.web.mvc.service;

import static edu.pnu.pnumenuselector.domain.data.entity.member.Role.NORMAL;

import edu.pnu.pnumenuselector.web.mvc.repository.AuthorityRepository;
import edu.pnu.pnumenuselector.domain.data.entity.member.Authority;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

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
    public void grant(Authority authority){
        authorityRepository.save(authority);
    }


    public Authority findOne(Long id){
        return authorityRepository.findById(id)
                .orElseThrow();
    }
}
