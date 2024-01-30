package potenday.pilsa.login.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "refreshToken")
public class RefreshToken {
    @Id
    private String refreshToken;

    private Long memberId;

    @TimeToLive
    @Value("${jwt.refresh-expiration-time}")
    private Long timeToLive;

    @Builder
    public RefreshToken(String refreshToken, Long memberId, Long timeToLive) {
        this.refreshToken = refreshToken;
        this.memberId = memberId;
        this.timeToLive = timeToLive;
    }
}
