package potenday.pilsa.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.apache.http.HttpHeaders.AUTHORIZATION;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000", "https://localhost:3000", "http://api.dev.textbucket.me", "http://www.api.dev.textbucket.me", "https://api.dev.textbucket.me", "https://www.api.dev.textbucket.me", "https://pilsa-fe.vercel.app")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .exposedHeaders(AUTHORIZATION)
                .allowCredentials(true);
    }
}
