package potenday.pilsa.pilsa.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    // 배경 색상
    @Column
    private String backgroundColor;

    // 대표 이미지 여부
    @Enumerated(value = EnumType.STRING)
    @Column
    private YN thumbnail;

    // 이미지 순서
    @Column
    private Integer imageSeq;

    private LocalDateTime registDate;

    private LocalDateTime updateDate;

}
