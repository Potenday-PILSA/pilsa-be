package potenday.pilsa.like.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import potenday.pilsa.like.domain.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Page<Like> findByMember_IdOrderByRegistDateDesc(Long memberId, Pageable pageable);

}
