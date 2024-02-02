package potenday.pilsa.pilsaBasicContents.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import potenday.pilsa.pilsaBasicContents.domain.PilsaBasicContents;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ResponseBasicPilsaListDto {

    @Schema(description = "전체 개수")
    private Long totalCount;
    @Schema(description = "메인화면 필사 리스트")
    private List<ResponseBasicPilsaDto> pilsaLists;

    @Builder
    public ResponseBasicPilsaListDto(Long totalCount, List<ResponseBasicPilsaDto> pilsaLists) {
        this.totalCount = totalCount;
        this.pilsaLists = pilsaLists;
    }

    public static ResponseBasicPilsaListDto from(Page<PilsaBasicContents> pilsaPage) {
        List<ResponseBasicPilsaDto> pilsaMainList = pilsaPage.getContent().stream()
                .map(pilsa -> ResponseBasicPilsaDto.from(pilsa))
                .collect(Collectors.toList());

        return ResponseBasicPilsaListDto.builder()
                .totalCount(pilsaPage.getTotalElements())
                .pilsaLists(pilsaMainList)
                .build();
    }

/*    public static ResponsePilsaMainListDto from(Page<Pilsa> pilsaList) {
        List<ResponsePilsaMainDto> pilsaMainList = pilsaList.stream()
                .map(ResponsePilsaMainDto::from).collect(Collectors.toList());

        return ResponsePilsaMainListDto.builder()
                .pilsaLists(pilsaMainList)
                .build();
    }*/
}