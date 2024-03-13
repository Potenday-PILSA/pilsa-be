package potenday.pilsa.pilsa.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class ResponsePilsaListDto {

    @Schema(description = "전체 개수")
    private Long totalCount;
    @Schema(description = "필사 리스트")
    private List<ResponsePilsaDetailDto> pilsaLists;

    @Builder
    public ResponsePilsaListDto(Long totalCount, List<ResponsePilsaDetailDto> pilsaLists) {
        this.totalCount = totalCount;
        this.pilsaLists = pilsaLists;
    }

    public static ResponsePilsaListDto from(List<ResponsePilsaDetailDto> pilsaLists, Long totalCount) {
        return ResponsePilsaListDto.builder()
                .totalCount(totalCount)
                .pilsaLists(pilsaLists)
                .build();
    }
}