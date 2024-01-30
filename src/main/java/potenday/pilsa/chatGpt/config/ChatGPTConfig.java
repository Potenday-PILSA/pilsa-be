package potenday.pilsa.chatGpt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Indexed;

import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import feign.Retryer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Configuration
@ConfigurationProperties(prefix = "openai-service")
@Indexed
@Data
@Slf4j
public class ChatGPTConfig {

    // OpenAI 서비스와의 통신을 위한 HTTP 클라이언트 설정
    @Value("${openai-service.http-client.read-timeout}")
    private int readTimeOut;

    @Value("${openai-service.http-client.connect-timeout}")
    private int connectTimeOut;

    // OpenAI API 키
    @Value("${openai-service.api-key}")
    private String apiKey;

    // 사용할 OpenAI 모델
    @Value("${openai-service.gpt-model}")
    private String model;

    // Feign 클라이언트에 API 키를 추가하기 위한 인터셉터
    @Bean
    public RequestInterceptor apiKeyInterceptor() {
        return request -> request.header("Authorization", "Bearer " + apiKey);
    }

    // Feign 클라이언트 설정 옵션
    @Bean
    public Request.Options options() {
        return new Request.Options(getConnectTimeOut(), getReadTimeOut());
    }

    // Feign 로깅 레벨
    @Bean
    public Logger.Level feignLogger() {
        return Logger.Level.FULL;
    }

    // Feign 재시도 정책
    @Bean
    public Retryer retryer() {
        return new Retryer.Default();
    }
}