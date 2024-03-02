package potenday.pilsa.pilsa.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class RequestCalenderPilsa {
    private LocalDate startDt;
    private LocalDate endDt;

    @Builder
    public RequestCalenderPilsa(LocalDate startDt, LocalDate endDt) {
        this.startDt = startDt;
        this.endDt = endDt;
    }
}
