package potenday.pilsa.pilsa.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import potenday.pilsa.pilsa.domain.Pilsa;
import potenday.pilsa.pilsa.domain.YN;
import potenday.pilsa.pilsaCategory.dto.response.ResponseCategoryDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ResponsePilsaMainListDto {

    @Schema(description = "전체 개수")
    private Long totalCount;
    @Schema(description = "메인화면 필사 리스트")
    private List<ResponsePilsaMainDto> pilsaLists;

    @Builder
    public ResponsePilsaMainListDto(Long totalCount, List<ResponsePilsaMainDto> pilsaLists) {
        this.totalCount = totalCount;
        this.pilsaLists = pilsaLists;
    }

    public static ResponsePilsaMainListDto from(Page<Pilsa> pilsaPage, List<Pilsa> allPilsas) {
        List<ResponsePilsaMainDto> pilsaMainList = pilsaPage.getContent().stream()
                .map(pilsa -> ResponsePilsaMainDto.from(pilsa, allPilsas))
                .collect(Collectors.toList());

        return ResponsePilsaMainListDto.builder()
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