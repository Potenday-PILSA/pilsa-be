package potenday.pilsa.global.dto.response;

import lombok.Getter;

@Getter
public class CustomErrorResponse {
    private int status;
    private String message;

    public CustomErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
