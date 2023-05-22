package sopt.org.MyRealTrip.exception.model;
import sopt.org.MyRealTrip.exception.Error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public class BusinessException extends RuntimeException{
    private final Error error;
    public int getHttpStatus() {
        return error.getHttpStatusCode();
    }
    public String getMessage() { return error.getMessage();}
}
