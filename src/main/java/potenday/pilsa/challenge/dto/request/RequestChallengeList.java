package potenday.pilsa.challenge.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import potenday.pilsa.global.dto.request.RequestPageDto;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RequestChallengeList extends RequestPageDto {

    @Schema(description = "챌린지 상태 조회 [ING: 진행중, END:진행종료, EXPECTED:진행예정]")
    private List<RequestStatus> status;

    public RequestChallengeList(Integer page, Integer size, List<RequestStatus> status) {
        super(page, size);
        this.status = status;
    }
}
