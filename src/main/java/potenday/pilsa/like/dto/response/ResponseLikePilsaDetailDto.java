package potenday.pilsa.like.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import potenday.pilsa.like.domain.Like;
import potenday.pilsa.member.dto.response.MemberInfoResponse;
import potenday.pilsa.pilsa.domain.Pilsa;
import potenday.pilsa.pilsa.domain.YN;
import potenday.pilsa.pilsaCategory.dto.response.ResponseCategoryDto;
import potenday.pilsa.pilsaImage.dto.response.ResponseImagetDto;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseLikePilsaDetailDto {

    @Schema(description = "필사 ID")
    private Long pilsaId;
    @Schema(description = "제목")
    private String title;
    @Schema(description = "저자")
    private String author;
    @Schema(description = "출판사")
    private String publisher;
    @Schema(description = "나만보기 여부")
    private YN privateType;
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
    @Schema(description = "카테고리 리스트")
    private List<ResponseCategoryDto> categoryLists;
    @Schema(description = "이미지 리스트")
    private List<ResponseImagetDto> pilsaImages;
    @Schema(description = "회원 정보")
    private MemberInfoResponse memberInfoResponse;

    @Builder
    public ResponseLikePilsaDetailDto(Long pilsaId, MemberInfoResponse memberInfoResponse, String title, List<ResponseCategoryDto> categoryLists, String author, String publisher, YN privateType, String textContents, String backgroundImageUrl, String backgroundColor, LocalDateTime registDate, LocalDateTime updateDate, List<ResponseImagetDto> pilsaImages) {
        this.pilsaId = pilsaId;
        this.memberInfoResponse = memberInfoResponse;
        this.title = title;
        this.categoryLists = categoryLists;
        this.author = author;
        this.publisher = publisher;
        this.privateType = privateType;
        this.textContents = textContents;
        this.backgroundImageUrl = backgroundImageUrl;
        this.backgroundColor = backgroundColor;
        this.registDate = registDate;
        this.updateDate = updateDate;
        this.pilsaImages = pilsaImages;
    }

    public static ResponseLikePilsaDetailDto from(Like like) {
        MemberInfoResponse member = MemberInfoResponse.from(like.getPilsa().getMember());
        List<ResponseCategoryDto> responseCategoryDtos = like.getPilsa().getRelationPilsaCategories()
                .stream()
                .map(relationPilsaCategory -> ResponseCategoryDto.from(relationPilsaCategory.getCategory()))
                .toList();
        List<ResponseImagetDto> responseImagetDtos = like.getPilsa().getPilsaImages()
                .stream()
                .map(ResponseImagetDto::from)
                .toList();


        return ResponseLikePilsaDetailDto.builder()
                .pilsaId(like.getPilsa().getPilsaId())
                .title(like.getPilsa().getTitle())
                .author(like.getPilsa().getAuthor())
                .publisher(like.getPilsa().getPublisher())
                .privateType(like.getPilsa().getPrivateType())
                .textContents(like.getPilsa().getTextContents())
                .backgroundImageUrl(like.getPilsa().getBackgroundImageUrl())
                .backgroundColor(like.getPilsa().getBackgroundColor())
                .registDate(like.getPilsa().getRegistDate())
                .updateDate(like.getPilsa().getUpdateDate())
                .categoryLists(responseCategoryDtos)
                .memberInfoResponse(member)
                .pilsaImages(responseImagetDtos)
                .build();
    }
}
