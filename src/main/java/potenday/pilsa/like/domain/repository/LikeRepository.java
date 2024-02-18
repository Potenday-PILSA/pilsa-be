package potenday.pilsa.like.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import potenday.pilsa.like.domain.Like;
import potenday.pilsa.member.domain.Member;
import potenday.pilsa.pilsa.domain.Pilsa;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Page<Like> findByMember_IdAndPilsa_DeleteDateIsNullOrderByRegistDateDesc(Long memberId, Pageable pageable);
    Optional<Like> findByMember_IdAndPilsa(Long member_id, Pilsa pilsa);
}
