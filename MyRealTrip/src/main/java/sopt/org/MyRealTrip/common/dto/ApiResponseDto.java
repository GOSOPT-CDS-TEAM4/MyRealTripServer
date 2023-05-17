package sopt.org.MyRealTrip.common.dto;

import sopt.org.MyRealTrip.exception.Success;
import sopt.org.MyRealTrip.exception.Error;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponseDto<T> {

    private final int code;
    private final String message;
    private T data;

    public static ApiResponseDto success(Success success) {
        return new ApiResponseDto<>(success.getHttpStatusCode(), success.getMessage());
    }

    public static <T> ApiResponseDto<T> success(Success success, T data) {
        return new ApiResponseDto<T>(success.getHttpStatusCode(), success.getMessage(), data);
    }

    public static ApiResponseDto error(Error error) {
        return new ApiResponseDto<>(error.getHttpStatusCode(), error.getMessage());
    }

    public static ApiResponseDto error(Error error, String message) {
        return new ApiResponseDto(error.getHttpStatusCode(), message);
    }
}
