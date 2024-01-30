package potenday.pilsa.chatGpt.dto.response;

import java.io.Serializable;

import lombok.Data;
import potenday.pilsa.chatGpt.dto.Message;

@Data
public class Choice implements Serializable {

    private Integer index;
    private Message message;
    private String finishReason;

}
