package potenday.pilsa.chatGpt.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Usage implements Serializable {

    @JsonProperty("prompt_tokens")
    private String promptTokens;
    @JsonProperty("completion_tokens")
    private String completionTokens;
    @JsonProperty("total_tokens")
    private String totalTokens;

}
