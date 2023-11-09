package edu.pnu.pnumenuselector.domain.service;

import edu.pnu.pnumenuselector.domain.repository.AuthorityRepository;
import edu.pnu.pnumenuselector.entity.member.Authority;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorityService {

    private final AuthorityRepository authorityRepository;
    public void grant(Authority authority){
        authorityRepository.save(authority);
    }

    public Authority findOne(Long id){
        return authorityRepository.findById(id)
                .orElseThrow();
    }
}
