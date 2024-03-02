package potenday.pilsa.pilsa.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import potenday.pilsa.global.util.LocalDateUtil;
import potenday.pilsa.pilsa.domain.Pilsa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ResponseCalenderPilsa {
    @Schema(description = "필사 ID")
    private Long pilsaId;
    @Schema(description = "필사 제목")
    private String title;
    @Schema(description = "필사 등록 날짜")
    private LocalDate registDate;

    @Builder
    public ResponseCalenderPilsa(Long pilsaId, String title, LocalDate registDate) {
        this.pilsaId = pilsaId;
        this.title = title;
        this.registDate = registDate;
    }

    public static List<ResponseCalenderPilsa> from(List<Pilsa> pilsas) {
        return pilsas.stream()
                .map(ResponseCalenderPilsa::from)
                .collect(Collectors.toList());
    }

    private static ResponseCalenderPilsa from(Pilsa pilsa) {
        return ResponseCalenderPilsa.builder()
                .pilsaId(pilsa.getPilsaId())
                .title(pilsa.getTitle())
                .registDate(LocalDateUtil.localDateTimeToDate(pilsa.getRegistDate()))
                .build();
    }
}
