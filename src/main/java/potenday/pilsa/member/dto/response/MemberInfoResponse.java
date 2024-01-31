package potenday.pilsa.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import potenday.pilsa.member.domain.Member;
import potenday.pilsa.member.domain.Status;

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
    @Schema(description = "회원 상태 [ACTIVE : 활성, IDLE : 휴면, RESIGN : 탈퇴]")
    private Status status;

    @Builder
    public MemberInfoResponse(Long id, String nickName, String imageUrl, String description, Status status) {
        this.id = id;
        this.nickName = nickName;
        this.imageUrl = imageUrl;
        this.description = description;
        this.status = status;
    }

    public static MemberInfoResponse from(Member member) {
        return MemberInfoResponse.builder()
                .id(member.getId())
                .nickName(member.getProfileNickName())
                .imageUrl(member.getProfileImageUrl())
                .description(member.getDescription())
                .status(member.getStatus())
                .build();
    }
}
