package potenday.pilsa.pilsa.domain.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.jsonwebtoken.security.Jwks;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import potenday.pilsa.like.domain.QLike;
import potenday.pilsa.pilsa.domain.Pilsa;
import potenday.pilsa.pilsa.domain.QPilsa;
import potenday.pilsa.pilsa.domain.YN;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PilsaQRepositoryImpl implements PilsaQRepository {

    private final JPAQueryFactory queryFactory;
    private final QPilsa pilsa = QPilsa.pilsa;
    private final QLike like = QLike.like;

    @Override
    public Optional<Pilsa> getNextAndPreviousPilsa(Long pilsaId, Long memberId, Boolean isNext) {
        // 기본 쿼리: pilsaId로 정렬하고 memberId로 필터링
        JPAQuery<Pilsa> baseQuery = queryFactory
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

    @Override
    public Page<Pilsa> findPilsaListSortedByLikes(Pageable pageable) {
        List<Pilsa> results = queryFactory.selectFrom(pilsa)
                .leftJoin(pilsa.likes, like)
                .where(pilsa.deleteDate.isNull())
                .where(pilsa.privateType.eq(YN.N))
                .groupBy(pilsa)
                .orderBy(like.count().desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory.selectFrom(pilsa)
                .fetch().size();

        return new PageImpl<>(results, pageable, total);
    }
}
