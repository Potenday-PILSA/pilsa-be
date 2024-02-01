package potenday.pilsa.pilsa.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import potenday.pilsa.pilsa.domain.Pilsa;
import potenday.pilsa.pilsa.domain.YN;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ResponsePilsaDetailDto {

        @Schema(description = "필사 ID")
        private Long pilsaId;
        @Schema(description = "필사 작성 회원 ID")
        private Long memberId;
        @Schema(description = "필사 작성 프로밍 닉네임")
        private String profileNickName;
        @Schema(description = "제목")
        private String title;

        @Schema(description = "카테고리 리스트")
        private List<ResponsePilsaCategoryDto> categoryLists;

        @Schema(description = "저자")
        private String author;
        @Schema(description = "출판사")
        private String publisher;
        @Schema(description = "나만보기 여부")
        private YN privateType;

        @Schema(description = "상위 필사 ID")
        private Long followPilsaId;
        @Schema(description = "상위 필사 제목")
        private String followPilsaTitle;
        @Schema(description = "상위 필사 작성 회원 ID")
        private Long followMemberId;
        @Schema(description = "상위 필사 작성 프로밍 닉네임")
        private String followProfileNickName;

        @Schema(description = "콘텐츠 문구")
        private String textContents;
        @Schema(description = "배경 이미지 url")
        private String backgroundImageUrl;
        @Schema(description = "배경 이미지 색상")
        private String backgroundColor;
        @Schema(description = "등록일자")
        private LocalDateTime registDate;
        @Schema(description = "수정일자")
        private LocalDateTime updateDate;

        @Schema(description = "이미지 리스트")
        private List<ResponsePilsaImageDto> pilsaImages;

    @Builder
    public ResponsePilsaDetailDto(
        Long pilsaId,
        Long memberId,
        String profileNickName,
        String title,
        List<ResponsePilsaCategoryDto> categoryLists,
        String author,
        String publisher,
        YN privateType,
        Long followPilsaId,
        String followPilsaTitle,
        Long followMemberId,
        String followProfileNickName,
        String textContents,
        String backgroundImageUrl,
        String backgroundColor,
        LocalDateTime registDate,
        LocalDateTime updateDate,
        List<ResponsePilsaImageDto> pilsaImages
    ){
        this.pilsaId = pilsaId;
        this.memberId = memberId;
        this.profileNickName = profileNickName;
        this.title = title;
        this.categoryLists = categoryLists;
        this.author = author;
        this.publisher = publisher;
        this.privateType = privateType;
        this.followPilsaId = followPilsaId;
        this.followPilsaTitle = followPilsaTitle;
        this.followMemberId = followMemberId;
        this.followProfileNickName = followProfileNickName;
        this.textContents = textContents;
        this.backgroundImageUrl = backgroundImageUrl;
        this.backgroundColor = backgroundColor;
        this.registDate = registDate;
        this.updateDate = updateDate;
        this.pilsaImages = pilsaImages;
    }

    public static ResponsePilsaDetailDto from(Pilsa pilsa, List<Pilsa> allPilsas) {

        List<ResponsePilsaCategoryDto> categoryDtoList = pilsa.getRelationPilsaCategories().stream()
                .map(ResponsePilsaCategoryDto::from).toList();

        List<ResponsePilsaImageDto> imageDtoList = pilsa.getPilsaImages().stream()
                .map(ResponsePilsaImageDto::from).toList();

        Pilsa followPilsa = allPilsas.stream()
                .filter(p -> p.getPilsaId().equals(pilsa.getFollowPilsaId()))
                .findFirst()
                .orElse(null); // followPilsaId에 해당하는 Pilsa 객체를 찾거나 없으면 null 반환


        return ResponsePilsaDetailDto.builder()
                .pilsaId(pilsa.getPilsaId())
                .memberId(pilsa.getMember().getId())
                .profileNickName(pilsa.getMember().getProfileNickName())
                .title(pilsa.getTitle())
                .categoryLists(categoryDtoList)
                .author(pilsa.getAuthor())
                .publisher(pilsa.getPublisher())
                .privateType(pilsa.getPrivateType())
                .followPilsaId(followPilsa != null ? followPilsa.getFollowPilsaId() : null)
                .followPilsaTitle(followPilsa != null ? followPilsa.getTitle() : null)
                .followMemberId(followPilsa != null ? followPilsa.getMember().getId() : null)
                .followProfileNickName(followPilsa != null ? followPilsa.getMember().getProfileNickName() : null)
                .textContents(pilsa.getTextContents())
                .backgroundImageUrl(pilsa.getBackgroundImageUrl())
                .backgroundColor(pilsa.getBackgroundColor())
                .registDate(pilsa.getRegistDate())
                .updateDate(pilsa.getUpdateDate())
                .pilsaImages(imageDtoList)
                .build();
    }

}
