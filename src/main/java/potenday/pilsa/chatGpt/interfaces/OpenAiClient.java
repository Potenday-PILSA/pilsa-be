package potenday.pilsa.chatGpt.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import potenday.pilsa.chatGpt.config.ChatGPTConfig;
import potenday.pilsa.chatGpt.dto.request.ChatGPTRequest;
import potenday.pilsa.chatGpt.dto.response.ChatGPTResponse;

@FeignClient(
        name = "openai-service",
        url = "${openai-service.urls.base-url}",
        configuration = ChatGPTConfig.class
)
public interface OpenAiClient {

    @PostMapping(value = "${openai-service.urls.chat-url}", headers = {"Content-Type=application/json"})
    ChatGPTResponse chat(@RequestBody ChatGPTRequest chatGPTRequest);
}
