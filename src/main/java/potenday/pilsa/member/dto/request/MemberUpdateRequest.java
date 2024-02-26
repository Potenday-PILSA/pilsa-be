package potenday.pilsa.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberUpdateRequest {

    @Schema(description = "회원의 이미지 Url")
    private String imageUrl;
    @Schema(description = "회원의 닉네임")
    @NotBlank(message = "닉네임은 필수 값 입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9가-힣]{1,10}$", message = "닉네임은 숫자, 영어, 한글로 이루어진 10자 이내의 값이어야 합니다.")
    private String nickName;

    @Size(max = 100, message = "소개글은 최대 100자로 작성할 수 있습니다.")
    private String description;

    @Builder
    public MemberUpdateRequest(String imageUrl, String nickName, String description) {
        this.imageUrl = imageUrl;
        this.nickName = nickName;
        this.description = description;
    }
}
