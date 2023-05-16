package sopt.org.MyRealTrip.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access =  AccessLevel.PRIVATE)
public enum Success {

    /**
     * 200 OK
     * */
    GET_RANDOM_TOURLIST_SUCCESS(HttpStatus.OK, "투어아이템 리스트 랜덤으로 가져오기 성공"),



    /**
     * 201 CREATED
     */
    CREATE_SCRAP_SUCCESS(HttpStatus.CREATED, "스크랩 성공"),
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}