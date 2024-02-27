package potenday.pilsa.pilsa.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import potenday.pilsa.global.dto.request.RequestPageDto;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class RequestPilsaList extends RequestPageDto {
    @Schema(description = "[POPULAR(인기순), FASTEST(최신순)]")
    private PilsaListType pilsaListType;

    public RequestPilsaList(Integer page, Integer size, PilsaListType pilsaListType) {
        super(page, size);
        this.pilsaListType = pilsaListType;
    }
}
