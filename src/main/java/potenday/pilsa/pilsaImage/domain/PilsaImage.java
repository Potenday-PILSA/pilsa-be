package potenday.pilsa.pilsaImage.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import potenday.pilsa.pilsa.domain.Pilsa;
import potenday.pilsa.relationPilsaCategory.domain.RelationPilsaCategory;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "pilsaImage")
public class PilsaImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    // 이미지 url
    @Column
    private String imageUrl;

    // 대표 이미지 여부
    @Enumerated(value = EnumType.STRING)
    @Column
    private YN thumbnail;

    // 이미지 순서
    @Column
    private Integer imageSeq;

    // 필사 정보
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "pilsaId")
    private Pilsa pilsa;

}
