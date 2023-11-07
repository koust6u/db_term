package edu.pnu.pnumenuselector.domain.repository;

import edu.pnu.pnumenuselector.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
