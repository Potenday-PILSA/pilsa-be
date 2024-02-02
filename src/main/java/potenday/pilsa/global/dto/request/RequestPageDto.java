package potenday.pilsa.global.dto.request;

import lombok.*;
import org.springframework.data.domain.Pageable;

import static org.springframework.data.domain.PageRequest.of;

@Getter
@Setter
@NoArgsConstructor
public class RequestPageDto {

    private Integer page;
    private Integer size;

    public Pageable toPageable() {
        return of(page, size);
    }

    @Builder
    public RequestPageDto(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }
}
