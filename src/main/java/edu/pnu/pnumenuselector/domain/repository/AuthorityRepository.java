package edu.pnu.pnumenuselector.domain.repository;

import edu.pnu.pnumenuselector.entity.member.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
