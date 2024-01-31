package potenday.pilsa.login.domain;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import potenday.pilsa.global.exception.AccessTokenException;
import potenday.pilsa.global.exception.AuthException;
import potenday.pilsa.global.exception.BadRequestException;
import potenday.pilsa.global.exception.ExceptionCode;
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

    public String createAccessToken(final Long memberId) {
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

    public Boolean isExpiredAccessAndValidRefreshToken(String accessToken, String refreshToken) {
        try {
            Long memberId = getMemberIdFromAccessToken(accessToken);
            validateRefreshToken(refreshToken, memberId);
            return false;
        } catch (AccessTokenException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void validateRefreshToken(String refreshToken, Long memberId) {
        RefreshToken token = refreshTokenRepository.findById(refreshToken).orElseThrow(
                NullPointerException::new
        );

        if (!memberId.equals(token.getMemberId())) {
            throw new NullPointerException();
        }
    }

    public void deleteRefreshToken(String refreshToken) {
        refreshTokenRepository.deleteById(refreshToken);
    }

    public Long getMemberIdFromAccessToken(String accessToken) {
        return Long.parseLong(parseJwt(accessToken).getSubject());
    }

    public Long getMemberIdFromExpiredJwtToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findById(token).orElseThrow(
                () -> new AuthException(ExceptionCode.EXPIRED_REFRESH_TOKEN)
        );

        return refreshToken.getMemberId();
    }

    private Claims parseJwt(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

        } catch (ExpiredJwtException e) {
            throw new AccessTokenException(ExceptionCode.EXPIRED_ACCESS_TOKEN);
        } catch (JwtException | IllegalArgumentException e) {
            throw new BadRequestException(ExceptionCode.INVALID_ACCESS_TOKEN);
        }
    }
}
