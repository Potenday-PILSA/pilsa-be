package potenday.pilsa.login;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import potenday.pilsa.login.domain.TokenProvider;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthArgumentResolver implements HandlerMethodArgumentResolver {
    private final AccessTokenExtractor accessTokenExtractor;
    private final TokenProvider tokenProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Auth.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Auth parameterAnnotation = parameter.getParameterAnnotation(Auth.class);

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String authorizationHeader = request.getHeader("Authorization");

        if (!parameterAnnotation.required() && authorizationHeader.isBlank()){
            return null;
        }

        String accessToken = accessTokenExtractor.extractAccessToken(authorizationHeader);

        return tokenProvider.getMemberIdFromAccessToken(accessToken);
    }
}
