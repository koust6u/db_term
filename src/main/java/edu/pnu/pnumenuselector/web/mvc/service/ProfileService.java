package edu.pnu.pnumenuselector.web.mvc.service;

import edu.pnu.pnumenuselector.web.mvc.repository.ProfileRepository;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import edu.pnu.pnumenuselector.domain.data.entity.member.Profile;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Transactional
    public Profile createByMember(Member member){
        Profile newProfile = Profile.builder()
                .member(member)
                .build();
        profileRepository.save(newProfile);

        return newProfile;
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

