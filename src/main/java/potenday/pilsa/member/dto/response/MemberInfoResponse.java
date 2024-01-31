package potenday.pilsa.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import potenday.pilsa.member.domain.Member;

@Getter
public class MemberInfoResponse {
    @Schema(description = "회원 ID")
    private Long id;
    @Schema(description = "회원 닉네임")
    private String nickName;
    @Schema(description = "회원 이미지 URL")
    private String imageUrl;

    @Schema(description = "회원 소개글")
    private String description;

    @Builder
    public MemberInfoResponse(Long id, String nickName, String imageUrl, String description) {
        this.id = id;
        this.nickName = nickName;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public static MemberInfoResponse from(Member member) {
        return MemberInfoResponse.builder()
                .id(member.getId())
                .nickName(member.getProfileNickName())
                .imageUrl(member.getProfileImageUrl())
                .description(member.getDescription())
                .build();
    }
}
