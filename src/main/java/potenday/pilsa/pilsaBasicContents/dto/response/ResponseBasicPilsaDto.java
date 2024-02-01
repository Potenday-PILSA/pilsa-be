package potenday.pilsa.pilsaBasicContents.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import potenday.pilsa.pilsa.domain.Pilsa;
import potenday.pilsa.pilsa.domain.YN;
import potenday.pilsa.pilsa.dto.response.ResponsePilsaMainCategoryDto;
import potenday.pilsa.pilsaBasicContents.domain.PilsaBasicContents;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ResponseBasicPilsaDto {

    @Schema(description = "필사 ID")
    private Long pilsaId;
    @Schema(description = "제목")
    private String title;

    @Schema(description = "카테고리 리스트")
    private List<ResponseBasicPilsaCategoryDto> categoryLists;

    @Schema(description = "저자")
    private String author;
    @Schema(description = "출판사")
    private String publisher;

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
    public ResponseBasicPilsaDto(
            Long pilsaId,
            String title,
            List<ResponseBasicPilsaCategoryDto> categoryLists,
            String author,
            String publisher,
            String textContents,
            String backgroundImageUrl,
            String backgroundColor,
            LocalDateTime registDate,
            LocalDateTime updateDate
    ) {
            this.pilsaId = pilsaId;
            this.title = title;
            this.categoryLists = categoryLists;
            this.author = author;
            this.publisher = publisher;
            this.textContents = textContents;
            this.backgroundImageUrl = backgroundImageUrl;
            this.backgroundColor = backgroundColor;
            this.registDate = registDate;
            this.updateDate =updateDate;
    }

    public static ResponseBasicPilsaDto from(PilsaBasicContents pilsaList) {
        List<ResponseBasicPilsaCategoryDto> categoryDtoList = pilsaList.getCategoryList().stream()
                .map(ResponseBasicPilsaCategoryDto::from).toList();

        return ResponseBasicPilsaDto.builder()
                .pilsaId(pilsaList.getBasicContentsId()).title(pilsaList.getTitle())
                .categoryLists(categoryDtoList)
                .author(pilsaList.getAuthor())
                .publisher(pilsaList.getPublisher())
                .textContents(pilsaList.getTextContents())
                .backgroundImageUrl(pilsaList.getBackgroundImageUrl())
                .backgroundColor(pilsaList.getBackgroundColor())
                .registDate(pilsaList.getRegistDate())
                .updateDate(pilsaList.getUpdateDate())
                .build();
    }

}
