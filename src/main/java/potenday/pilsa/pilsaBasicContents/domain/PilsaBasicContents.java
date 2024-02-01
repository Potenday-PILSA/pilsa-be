package potenday.pilsa.pilsaBasicContents.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import potenday.pilsa.pilsaCategory.domain.PilsaCategory;
import potenday.pilsa.pilsaImage.domain.PilsaImage;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "pilsaBasicContents")
public class PilsaBasicContents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long basicContentsId;


    // 콘텐츠 제목
    @Column
    private String title;

    // 콘텐츠 문구
    @Column
    private String textContents;

    // 배경 이미지 url
    @Column
    private String backgroundImageUrl;

    // 배경 색상
    @Column
    private String backgroundColor;

    // 저자
    @Column
    private String author;

    // 출판사
    @Column
    private String publisher;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "relation_basic_pilsa_category",
            joinColumns = @JoinColumn(name = "basic_contents_id"),
            inverseJoinColumns = @JoinColumn(name = "category_cd")
    )
    List<PilsaCategory> categoryList;


    private LocalDateTime registDate;

    private LocalDateTime updateDate;
}

