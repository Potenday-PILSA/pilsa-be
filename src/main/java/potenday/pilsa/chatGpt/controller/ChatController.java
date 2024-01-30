package potenday.pilsa.chatGpt.controller;

import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import feign.Retryer;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Indexed;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import potenday.pilsa.chatGpt.config.ChatGPTConfig;
import potenday.pilsa.chatGpt.dto.Message;
import potenday.pilsa.chatGpt.dto.request.ChatGPTRequest;
import potenday.pilsa.chatGpt.dto.request.ChatRequest;
import potenday.pilsa.chatGpt.dto.response.ChatGPTResponse;
import potenday.pilsa.chatGpt.interfaces.OpenAiClient;
import potenday.pilsa.chatGpt.service.ChatService;

import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ChatController {
    private final ChatgptService chatgptService;
    private final ChatService chatService;

    @PostMapping(value = "/chat", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ChatGPTResponse chat(@RequestBody ChatRequest chatRequest) {
        return chatService.chat(chatRequest);
    }
}