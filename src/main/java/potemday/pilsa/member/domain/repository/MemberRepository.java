package potemday.pilsa.member.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import potemday.pilsa.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
