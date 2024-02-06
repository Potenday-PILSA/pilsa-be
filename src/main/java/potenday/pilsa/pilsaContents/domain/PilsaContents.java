/*
package potenday.pilsa.pilsaContents.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import potenday.pilsa.pilsaImage.domain.PilsaImage;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "pilsaContents")
public class PilsaContents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentsId;

    // 콘텐츠 문구
    @Column
    private String content;

    // 배경 이미지 url
    @Column
    private String backgroundImageUrl;

    // 배경 색상
    @Column
    private String backgroundColor;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "contentId")
    List<PilsaImage> pilsaImages;

    private LocalDateTime registDate;

    private LocalDateTime updateDate;
}
*/
