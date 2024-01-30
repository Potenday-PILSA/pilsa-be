package potenday.pilsa.chatGpt.dto.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import potenday.pilsa.chatGpt.dto.Message;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatGPTRequest implements Serializable {

    private String model;
    private List<Message> messages;
}
