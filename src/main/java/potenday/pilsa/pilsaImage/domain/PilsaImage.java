package potenday.pilsa.pilsaImage.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import potenday.pilsa.pilsa.domain.Pilsa;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "pilsaImage")
@ToString
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pilsaId")
    private Pilsa pilsa;

    public PilsaImage(String imageUrl, YN thumbnail, Integer imageSeq, Pilsa pilsa) {
        this.imageUrl = imageUrl;
        this.thumbnail = thumbnail;
        this.imageSeq = imageSeq;
        this.pilsa = pilsa;
    }

}
