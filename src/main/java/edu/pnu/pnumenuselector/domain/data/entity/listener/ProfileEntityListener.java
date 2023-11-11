package edu.pnu.pnumenuselector.domain.data.entity.listener;

import edu.pnu.pnumenuselector.domain.data.entity.BaseEntity;
import edu.pnu.pnumenuselector.domain.data.entity.member.Profile;
import jakarta.persistence.PrePersist;

public class ProfileEntityListener {
    private static long count = 1;
    @PrePersist
    public void prePersist(Profile profile){
        if (profile.getName() == null){
            profile.setName(count++ + "번째 사용자");
        }
    }
}
