package potenday.pilsa.pilsa.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import potenday.pilsa.global.exception.BadRequestException;
import potenday.pilsa.global.exception.ExceptionCode;
import potenday.pilsa.member.domain.Member;
import potenday.pilsa.member.domain.Status;
import potenday.pilsa.pilsa.domain.Pilsa;
import potenday.pilsa.pilsa.domain.YN;
import potenday.pilsa.pilsaCategory.domain.PilsaCategory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ResponsePilsaMainDto {

    @Schema(description = "필사 ID")
    private Long pilsaId;
    @Schema(description = "필사 작성 회원 ID")
    private Long memberId;
    @Schema(description = "필사 작성 프로밍 닉네임")
    private String profileNickName;
    @Schema(description = "제목")
    private String title;

    @Schema(description = "카테고리 리스트")
    private List<ResponsePilsaMainCategoryDto> categoryLists;

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

    @Builder
    public ResponsePilsaMainDto(
            Long pilsaId,
            Long memberId,
            String profileNickName,
            String title,
            List<ResponsePilsaMainCategoryDto> categoryLists,
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
            LocalDateTime updateDate
    ) {
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
            this.updateDate =updateDate;
    }

    public static ResponsePilsaMainDto from(Pilsa pilsaList, List<Pilsa> allPilsas) {
        List<ResponsePilsaMainCategoryDto> categoryDtoList = pilsaList.getRelationPilsaCategories().stream()
                .map(ResponsePilsaMainCategoryDto::from).toList();

        Pilsa followPilsa = allPilsas.stream()
                .filter(p -> p.getPilsaId().equals(pilsaList.getFollowPilsaId()))
                .findFirst()
                .orElse(null); // followPilsaId에 해당하는 Pilsa 객체를 찾거나 없으면 null 반환


        return ResponsePilsaMainDto.builder()
                .pilsaId(pilsaList.getPilsaId())
                .memberId(pilsaList.getMember().getId())
                .profileNickName(pilsaList.getMember().getProfileNickName())
                .title(pilsaList.getTitle())
                .categoryLists(categoryDtoList)
                .author(pilsaList.getAuthor())
                .publisher(pilsaList.getPublisher())
                .privateType(pilsaList.getPrivateType())
                .followPilsaId(followPilsa != null ? followPilsa.getFollowPilsaId() : null)
                .followPilsaTitle(followPilsa != null ? followPilsa.getTitle() : null)
                .followMemberId(followPilsa != null ? followPilsa.getMember().getId() : null)
                .followProfileNickName(followPilsa != null ? followPilsa.getMember().getProfileNickName() : null)
                .textContents(pilsaList.getTextContents())
                .backgroundImageUrl(pilsaList.getBackgroundImageUrl())
                .backgroundColor(pilsaList.getBackgroundColor())
                .registDate(pilsaList.getRegistDate())
                .updateDate(pilsaList.getUpdateDate())
                .build();
    }

}
