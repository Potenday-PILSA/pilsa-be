package potenday.pilsa.global.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import static lombok.AccessLevel.PROTECTED;
import static org.springframework.data.domain.PageRequest.of;

@Getter
@Setter
@NoArgsConstructor
public class RequestPageDto {

    private Integer page;
    private Integer size;

    public Pageable pageable() {
        return of(page, size);
    }

    @Builder
    public RequestPageDto(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }
}
