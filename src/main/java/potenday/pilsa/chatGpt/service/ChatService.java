package potenday.pilsa.chatGpt.service;

import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import potenday.pilsa.chatGpt.config.ChatGPTConfig;
import potenday.pilsa.chatGpt.dto.Message;
import potenday.pilsa.chatGpt.dto.request.ChatGPTRequest;
import potenday.pilsa.chatGpt.dto.request.ChatRequest;
import potenday.pilsa.chatGpt.dto.response.ChatGPTResponse;
import potenday.pilsa.chatGpt.interfaces.OpenAiClient;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final OpenAiClient openAIClient;
    private final ChatGPTConfig openAIClientConfig;

    private final static String ROLE_USER = "user";

    public ChatGPTResponse chat(ChatRequest chatRequest) {

        Message message = Message.builder().role(ROLE_USER).content(chatRequest.getQuestion()).build();

        ChatGPTRequest chatGPTRequest = ChatGPTRequest.builder().model(openAIClientConfig.getModel())
                .messages(Collections.singletonList(message)).build();

        return openAIClient.chat(chatGPTRequest);
    }
}
