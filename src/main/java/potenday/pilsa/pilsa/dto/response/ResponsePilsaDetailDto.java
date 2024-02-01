package potenday.pilsa.pilsa.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import potenday.pilsa.pilsa.domain.YN;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
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
        private List<CategoryList> categoryLists;

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
        private List<ImageList> pilsaImages;

        @Getter
        @Setter
        @ToString
        @Builder
        public static class ImageList {
            @Schema(description = "이미지 ID")
            private Long imageId;
            @Schema(description = "이미지 url")
            private String imageUrl;
            @Schema(description = "이미지 ID")
            private potenday.pilsa.pilsaImage.domain.YN thumbnail;
            @Schema(description = "이미지 순서")
            private Integer imageSeq;
        }

        @Getter
        @Setter
        @ToString
        @Builder
        public static class CategoryList {
            @Schema(description = "카테고리 코드")
            private Long categoryCd;
            @Schema(description = "카테고리 명")
            private String categoryName;
            @Schema(description = "카테고리 설명")
            private String categoryDescription;
        }
}
