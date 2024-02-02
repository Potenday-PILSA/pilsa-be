package potenday.pilsa.pilsa.domain;

import jakarta.persistence.*;
import lombok.*;
import potenday.pilsa.global.exception.BadRequestException;
import potenday.pilsa.global.exception.ExceptionCode;
import potenday.pilsa.member.domain.Member;
import potenday.pilsa.pilsa.dto.request.RequestPilsaInfoDto;
import potenday.pilsa.pilsaImage.domain.PilsaImage;
import potenday.pilsa.pilsaImage.dto.request.ImageRequest;
import potenday.pilsa.relationPilsaCategory.domain.RelationPilsaCategory;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "pilsaInfo")
public class Pilsa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pilsaId;

    // 필사 작성 회원 ID
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", referencedColumnName = "id")
    private Member member;

    // 필사 카테코리
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pilsaIdCategory")
    List<RelationPilsaCategory> relationPilsaCategories;

    // 제목
    @Column
    private String title;

    // 상태값
    @Enumerated(value = EnumType.STRING)
    @Column
    private Status status;

    // 저자
    @Column
    private String author;

    // 출판사
    @Column
    private String publisher;

    // 나만보기 여부
    @Enumerated(value = EnumType.STRING)
    @Column
    private YN privateType;

    // 상위 필사 ID
    @Column
    private Long followPilsaId;

    // 콘텐츠 문구
    @Column
    private String textContents;

    // 배경 이미지 url
    @Column
    private String backgroundImageUrl;

    // 배경 색상
    @Column
    private String backgroundColor;

    // 필사 이미지
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pilsa")
    private List<PilsaImage> pilsaImages;

    private LocalDateTime registDate;

    private LocalDateTime updateDate;

    private LocalDateTime deleteDate;

    @Builder
    public Pilsa(String title, Member member,String author, String publisher, String textContents, String backgroundImageUrl, String backgroundColor, RequestPilsaInfoDto requestPilsaInfoDto) {
        validationContent(requestPilsaInfoDto.getImages(), textContents);

        this.title = title;
        this.member = member;
        this.author = author;
        this.publisher = publisher;
        this.privateType = YN.N; // TODO : 아직 기능 X
//        this.followPilsaId = followPilsaId; TODO : 아직 기능 없음
        this.textContents = textContents;
        this.backgroundColor = backgroundColor;
        this.backgroundImageUrl = backgroundImageUrl;
        this.registDate = LocalDateTime.now();

        if (!requestPilsaInfoDto.getImages().isEmpty()) {
            this.status = (textContents != null) ? Status.ALL : Status.IMG;
        } else {
            this.status = Status.TXT;
        }
    }

    public void updatePilsa(RequestPilsaInfoDto requestPilsaInfoDto) {
        this.title = requestPilsaInfoDto.getTitle();
        this.author = requestPilsaInfoDto.getAuthor();
        this.publisher = requestPilsaInfoDto.getPublisher();
        this.textContents = requestPilsaInfoDto.getTextContents();
        this.backgroundColor = requestPilsaInfoDto.getBackgroundColor();
        this.backgroundImageUrl = requestPilsaInfoDto.getBackgroundImageUrl();
        this.updateDate = LocalDateTime.now();

        this.getPilsaImages().clear();
        this.getRelationPilsaCategories().clear();

        if (!requestPilsaInfoDto.getImages().isEmpty()) {
            this.status = (textContents != null) ? Status.ALL : Status.IMG;
        } else {
            this.status = Status.TXT;
        }
    }

    public void setRelationPilsaCategories(List<RelationPilsaCategory> relationPilsaCategories) {
        this.relationPilsaCategories = relationPilsaCategories;
        for (RelationPilsaCategory relation : relationPilsaCategories) {
            relation.setPilsa(this);
        }
    }

    public void setCategories(List<RelationPilsaCategory> relationPilsaCategories) {
        this.relationPilsaCategories = relationPilsaCategories;
    }

    public void setPilsaImages(List<PilsaImage> pilsaImages) {
        this.pilsaImages = pilsaImages;
    }

    public void deletePilsa() {
        this.deleteDate = LocalDateTime.now();
    }

    private void validationContent(List<ImageRequest> images, String textContents) {
        if (images.isEmpty() && textContents.isBlank()) {
            throw new BadRequestException(ExceptionCode.NOT_INPUT_CONTENT);
        }
    }
}