package potenday.pilsa.pilsa.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import potenday.pilsa.member.dto.response.MemberInfoResponse;
import potenday.pilsa.pilsa.domain.Pilsa;
import potenday.pilsa.pilsa.domain.YN;
import potenday.pilsa.pilsaCategory.dto.response.ResponseCategoryDto;
import potenday.pilsa.pilsaImage.dto.response.ResponseImagetDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor
public class ResponsePilsaIncludeDetailDto {
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
    @Schema(description = "다음 필사 존재 여부")
    private Boolean isNextPilsa;
    @Schema(description = "이전 필사 존재 여부")
    private Boolean isPreviousPilsa;
    @Schema(description = "다음 필사 내용")
    private ResponseNextPreviousPilsaDto nextPilsa;
    @Schema(description = "이전 필사 내용")
    private ResponseNextPreviousPilsaDto previousPilsa;
    @Schema(description = "내가 좋아요한 여부")
    private Boolean isLikedAble;
    @Schema(description = "좋아요 개수")
    private Integer likeCount;

    @Builder
    public ResponsePilsaIncludeDetailDto(Long pilsaId, String title, String author, String publisher, YN privateType, String textContents, String backgroundImageUrl, String backgroundColor, LocalDateTime registDate, LocalDateTime updateDate, List<ResponseCategoryDto> categoryLists, List<ResponseImagetDto> pilsaImages, MemberInfoResponse memberInfoResponse, Boolean isNextPilsa, Boolean isPreviousPilsa, ResponseNextPreviousPilsaDto nextPilsa, ResponseNextPreviousPilsaDto previousPilsa, Boolean isLikedAble, Integer likeCount) {
        this.pilsaId = pilsaId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.privateType = privateType;
        this.textContents = textContents;
        this.backgroundImageUrl = backgroundImageUrl;
        this.backgroundColor = backgroundColor;
        this.registDate = registDate;
        this.updateDate = updateDate;
        this.categoryLists = categoryLists;
        this.pilsaImages = pilsaImages;
        this.memberInfoResponse = memberInfoResponse;
        this.isNextPilsa = isNextPilsa;
        this.isPreviousPilsa = isPreviousPilsa;
        this.nextPilsa = nextPilsa;
        this.previousPilsa = previousPilsa;
        this.isLikedAble = isLikedAble;
        this.likeCount = likeCount;
    }

    public static ResponsePilsaIncludeDetailDto from(Pilsa pilsa, Optional<Pilsa> nextPilsa, Optional<Pilsa> previousPilsa, Boolean isLiked) {
        MemberInfoResponse member = MemberInfoResponse.from(pilsa.getMember());
        List<ResponseCategoryDto> responseCategoryDtos = pilsa.getRelationPilsaCategories()
                .stream()
                .map(relationPilsaCategory -> ResponseCategoryDto.from(relationPilsaCategory.getCategory()))
                .toList();
        List<ResponseImagetDto> responseImagetDtos = pilsa.getPilsaImages()
                .stream()
                .map(ResponseImagetDto::from)
                .toList();

        return ResponsePilsaIncludeDetailDto.builder()
                .pilsaId(pilsa.getPilsaId())
                .title(pilsa.getTitle())
                .author(pilsa.getAuthor())
                .publisher(pilsa.getPublisher())
                .privateType(pilsa.getPrivateType())
                .textContents(pilsa.getTextContents())
                .backgroundImageUrl(pilsa.getBackgroundImageUrl())
                .backgroundColor(pilsa.getBackgroundColor())
                .registDate(pilsa.getRegistDate())
                .updateDate(pilsa.getUpdateDate())
                .categoryLists(responseCategoryDtos)
                .memberInfoResponse(member)
                .pilsaImages(responseImagetDtos)
                .isNextPilsa(nextPilsa.isPresent())
                .isPreviousPilsa(previousPilsa.isPresent())
                .nextPilsa(nextPilsa.map(ResponseNextPreviousPilsaDto::from).orElse(null))
                .previousPilsa(previousPilsa.map(ResponseNextPreviousPilsaDto::from).orElse(null))
                .isLikedAble(isLiked)
                .likeCount(pilsa.getLikes().size())
                .build();
    }
}
