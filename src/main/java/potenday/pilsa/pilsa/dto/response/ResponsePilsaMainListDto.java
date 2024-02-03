package potenday.pilsa.pilsa.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import potenday.pilsa.pilsa.domain.Pilsa;

import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class ResponsePilsaMainListDto {

    @Schema(description = "전체 개수")
    private Long totalCount;
    @Schema(description = "메인화면 필사 리스트")
    private List<ResponsePilsaDetailDto> pilsaLists;

    @Builder
    public ResponsePilsaMainListDto(Long totalCount, List<ResponsePilsaDetailDto> pilsaLists) {
        this.totalCount = totalCount;
        this.pilsaLists = pilsaLists;
    }

    public static ResponsePilsaMainListDto from(Page<Pilsa> pilsaPage) {
        List<ResponsePilsaDetailDto> responsePilsaDetailDtos = pilsaPage.getContent().stream()
                .map(ResponsePilsaDetailDto::from)
                .toList();

        return ResponsePilsaMainListDto.builder()
                .totalCount(pilsaPage.getTotalElements())
                .pilsaLists(responsePilsaDetailDtos)
                .build();
    }
}