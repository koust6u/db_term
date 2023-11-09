package edu.pnu.pnumenuselector.domain.service;

import edu.pnu.pnumenuselector.domain.repository.ProfileRepository;
import edu.pnu.pnumenuselector.entity.member.Member;
import edu.pnu.pnumenuselector.entity.member.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Transactional
    public void createByMember(Member member){
        Profile newProfile = Profile.builder()
                .member(member)
                .name("dummy")
                .build();
        profileRepository.save(newProfile);
    }

    @Transactional
    public void create(Profile profile){
        profileRepository.save(profile);
    }
    public Profile findOne(Long id){
        return profileRepository.findById(id)
                .orElseThrow();
    }
}

