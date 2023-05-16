package sopt.org.MyRealTrip.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Error {

    /**
     * 400 BAD REQUEST
     */
    REQUEST_VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 요청입니다"),

    /**
     * 404 NOT FOUND
     */
    NOT_FOUND_USER_EXCEPTION(HttpStatus.NOT_FOUND, "없는 유저입니다"),
    NOT_FOUND_TOUR_EXCEPTION(HttpStatus.NOT_FOUND, "없는 투어 아이템입니다"),
    NOT_FOUND_SCRAP_EXCEPTION(HttpStatus.NOT_FOUND,"스크랩된 기록이 없습니다."),

    /**
     * 409 CONFLICT
     */
    ALREADY_SCRAP_EXCEPTION(HttpStatus.CONFLICT, "해당 투어 아이템은 이미 스크랩 되었습니다."),


    /**
     * 500 INTERNAL SERVER ERROR
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러"),
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}