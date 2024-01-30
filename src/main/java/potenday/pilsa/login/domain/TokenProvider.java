package potenday.pilsa.login.domain;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import potenday.pilsa.login.domain.repository.RefreshTokenRepository;
import potenday.pilsa.login.dto.response.TokenPair;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Component
public class TokenProvider {
    private final SecretKey secretKey;
    private final Long accessTokenExpirationTime;
    private final Long refreshTokenExpirationTime;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenProvider(
            @Value("${jwt.secret-key}") final String secretKey,
            @Value("${jwt.access-expiration-time}") final Long accessTokenExpirationTime,
            @Value("${jwt.refresh-expiration-time}") final Long refreshTokenExpirationTime,
            RefreshTokenRepository refreshTokenRepository) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.accessTokenExpirationTime = accessTokenExpirationTime;
        this.refreshTokenExpirationTime = refreshTokenExpirationTime;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public TokenPair createTokenPair(final Long memberId) {
        String accessToken = createAccessToken(memberId);
        String refreshToken = createRefreshToken();

        saveRefreshToken(memberId, refreshToken);

        return TokenPair.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .refreshTokenExpirationTime(refreshTokenExpirationTime)
                .build();
    }

    private String createAccessToken(final Long memberId) {
        final Date now = new Date();
        final Date expirationDate = new Date(now.getTime() + accessTokenExpirationTime);

        return Jwts.builder()
                .subject(memberId.toString())
                .issuedAt(now)
                .expiration(expirationDate)
                .signWith(secretKey)
                .compact();
    }

    private String createRefreshToken() {
        return UUID.randomUUID().toString();
    }

    private void saveRefreshToken(final Long memberId, String refreshToken) {
        refreshTokenRepository.save(
                RefreshToken.builder()
                        .refreshToken(refreshToken)
                        .memberId(memberId)
                        .build()
        );
    }
}
