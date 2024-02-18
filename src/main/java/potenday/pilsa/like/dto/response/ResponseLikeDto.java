package potenday.pilsa.like.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import potenday.pilsa.like.domain.LikeType;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseLikeDto {
    @Schema(description = "필사 아이디")
    private Long pilsaId;
    @Schema(description = "좋아요 등록, 삭제 타입 좋아요가 등록 되었다면 ADD 삭제되었다면 DELETE  [ADD, DELETE]")
    private LikeType likeType;
    @Schema(description = "좋아요 기능 성공 여부")
    private Boolean isSuccess;


    public ResponseLikeDto(Long pilsaId, LikeType likeType) {
        this.pilsaId = pilsaId;
        this.likeType = likeType;
        this.isSuccess = true;
    }
}
