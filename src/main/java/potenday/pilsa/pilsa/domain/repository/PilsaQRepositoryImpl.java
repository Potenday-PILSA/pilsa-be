package potenday.pilsa.pilsa.domain.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.jsonwebtoken.security.Jwks;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import potenday.pilsa.pilsa.domain.Pilsa;
import potenday.pilsa.pilsa.domain.QPilsa;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PilsaQRepositoryImpl implements PilsaQRepository {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Optional<Pilsa> getNextAndPreviousPilsa(Long pilsaId, Long memberId, Boolean isNext) {
        QPilsa pilsa = QPilsa.pilsa;

        JPAQuery<Pilsa> query = new JPAQuery<>(entityManager);

        // 기본 쿼리: pilsaId로 정렬하고 memberId로 필터링
        JPAQuery<Pilsa> baseQuery = query
                .select(pilsa)
                .from(pilsa)
                .where(pilsa.deleteDate.isNull());

        if (memberId != null) {
            baseQuery.where(pilsa.member.id.eq(memberId));
        }

        // 다음 게시물 또는 이전 게시물 가져오기
        if (isNext) {
            return Optional.ofNullable(
                    baseQuery
                            .where(pilsa.pilsaId.gt(pilsaId))
                            .orderBy(pilsa.pilsaId.asc())
                            .fetchFirst()
            );
        } else {
            return Optional.ofNullable(
                    baseQuery
                            .where(pilsa.pilsaId.lt(pilsaId))
                            .orderBy(pilsa.pilsaId.desc())
                            .fetchFirst()
            );
        }
    }
}
