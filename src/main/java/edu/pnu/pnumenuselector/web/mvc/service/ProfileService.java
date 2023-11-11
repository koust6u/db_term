package edu.pnu.pnumenuselector.web.mvc.service;

import edu.pnu.pnumenuselector.domain.data.dto.profile.ProfileDto;
import edu.pnu.pnumenuselector.web.mvc.repository.ProfileRepository;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import edu.pnu.pnumenuselector.domain.data.entity.member.Profile;
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

    @Transactional
    public ProfileDto update(ProfileDto response, Member member){
        Long id = member.getProfile()
                .getId();
        Profile findProfile = findOne(id);
        findProfile.update(response);
        return findProfile.toResponse();
    }
}

