package potenday.pilsa.login.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import potenday.pilsa.member.domain.SocialType;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberInfoResponse {
    private SocialType socialType;
    private String memberKey;
    private String imageUrl;
    private String nickName;
    private String email;

    @Builder
    public MemberInfoResponse(SocialType socialType, String memberKey, String imageUrl, String nickName, String email) {
        this.socialType = socialType;
        this.memberKey = memberKey;
        this.imageUrl = imageUrl;
        this.nickName = nickName;
        this.email = email;
    }
}
