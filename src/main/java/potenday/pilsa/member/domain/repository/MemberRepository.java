package potenday.pilsa.member.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import potenday.pilsa.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
