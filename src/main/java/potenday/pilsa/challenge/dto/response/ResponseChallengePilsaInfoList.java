package potenday.pilsa.challenge.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import potenday.pilsa.pilsa.domain.Pilsa;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseChallengePilsaInfoList {
    private List<ResponseChallengePilsaInfo> responseChallengePilsaInfos;

    @Builder
    public ResponseChallengePilsaInfoList(List<ResponseChallengePilsaInfo> responseChallengePilsaInfos) {
        this.responseChallengePilsaInfos = responseChallengePilsaInfos;
    }

    public static ResponseChallengePilsaInfoList from(List<Pilsa> pilsas) {
        List<ResponseChallengePilsaInfo> responsePilsas = pilsas.stream()
                .map(ResponseChallengePilsaInfo::from)
                .toList();

        return ResponseChallengePilsaInfoList.builder()
                .responseChallengePilsaInfos(responsePilsas)
                .build();
    }
}
