package potenday.pilsa.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import potenday.pilsa.login.AuthArgumentResolver;

import java.util.List;

// HTTP 요청이 들어오면 스프링에서 자동으로 파라미터를 해석하는데 우리는 토큰을 자체적으로 해석해야되므로
// 커스텀 인자 리졸버를 등록하는 과정이 필요하다.
@Configuration
@RequiredArgsConstructor
public class AuthConfig implements WebMvcConfigurer {
    private final AuthArgumentResolver authArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authArgumentResolver);
    }
}
