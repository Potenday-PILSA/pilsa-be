package potenday.pilsa.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {
    INVALID_REQUEST(1000, "올바르지 않은 요청입니다."),

    NOT_FOUND_MEMBER(2001, "회원을 찾을 수 없습니다."),
    NOT_FOUND_PILSA(2002, "필사를 찾을 수 없습니다."),

    MAX_CATEGORY_SIZE(3001, "필사를 등록할때 카테고리는 최대 3개까지 선택할 수 있습니다."),
    NOT_INPUT_CONTENT(3002, "필사를 등록할때 필사 내용과 사진 중 하나는 필수로 등록해주세요."),
    IMAGE_COUNT_MUST_ONE(3003, "필사를 등록할때 사진은 한장만 업로드 할 수 있습니다."),

    NOT_FOUND_PILSA_CATEGORY(2003, "필사카테고리를 찾을 수 없습니다."),

    EXPIRED_ACCESS_TOKEN(5101, "만료된 액세스 토큰입니다."),
    EXPIRED_REFRESH_TOKEN(5102, "만료된 리프레시 토큰입니다."),
    INVALID_ACCESS_TOKEN(5103, "유효하지 않은 액세스 토큰입니다."),
    INVALID_REFRESH_TOKEN(5104, "유효하지 않은 리프레시 토큰입니다."),
    FAIL_TO_VALIDATE_TOKEN(5105, "토큰 유효성 검사에 실패했습니다."),
    INVALID_IMAGE_FILE(6001, "이미지 파일을 업로드해주세요."),
    INVALID_IMAGE_PATH(6002, "유효하지 않은 이미지 경로입니다."),
    FAIL_TO_UPLOAD_IMAGE(6003, "이미지 파일 업로드에 실패했습니다."),
    INTERNAL_SEVER_ERROR(9999, "서버 에러가 발생하였습니다. 관리자에게 문의해 주세요.");

    private final int code;
    private final String message;
}
