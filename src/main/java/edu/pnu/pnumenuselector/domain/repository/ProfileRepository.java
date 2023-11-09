package edu.pnu.pnumenuselector.domain.repository;

import edu.pnu.pnumenuselector.entity.member.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
