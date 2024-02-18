package potenday.pilsa.like.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import potenday.pilsa.like.domain.Like;
import potenday.pilsa.pilsa.domain.Pilsa;
import potenday.pilsa.pilsa.dto.response.ResponsePilsaDetailDto;

import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class ResponseLikePilsaListDto {

    @Schema(description = "전체 개수")
    private Long totalCount;
    @Schema(description = "메인화면 필사 리스트")
    private List<ResponseLikePilsaDetailDto> pilsaLists;

    @Builder
    public ResponseLikePilsaListDto(Long totalCount, List<ResponseLikePilsaDetailDto> pilsaLists) {
        this.totalCount = totalCount;
        this.pilsaLists = pilsaLists;
    }

    public static ResponseLikePilsaListDto from(Page<Like> likePage) {
        List<ResponseLikePilsaDetailDto> responsePilsaDetailDtos = likePage.getContent().stream()
                .map(ResponseLikePilsaDetailDto::from)
                .toList();

        return ResponseLikePilsaListDto.builder()
                .totalCount(likePage.getTotalElements())
                .pilsaLists(responsePilsaDetailDtos)
                .build();
    }
}