package edu.pnu.pnumenuselector.domain.service;

import edu.pnu.pnumenuselector.domain.repository.ProfileRepository;
import edu.pnu.pnumenuselector.entity.member.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public void create(Profile profile){
        profileRepository.save(profile);
    }

    public Profile findOne(Long id){
        return profileRepository.findById(id)
                .orElseThrow();
    }
}

