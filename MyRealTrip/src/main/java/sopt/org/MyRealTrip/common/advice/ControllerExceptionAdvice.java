package sopt.org.MyRealTrip.common.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sopt.org.MyRealTrip.common.dto.ApiResponseDto;
import sopt.org.MyRealTrip.exception.Error;
import sopt.org.MyRealTrip.exception.model.BusinessException;
import java.util.Objects;

@RestControllerAdvice
public class ControllerExceptionAdvice {

    /**
     * 400 BAD_REQUEST
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ApiResponseDto handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        FieldError fieldError = Objects.requireNonNull(e.getFieldError());
        return ApiResponseDto.error(Error.REQUEST_VALIDATION_EXCEPTION, String.format("%s. (%s)", fieldError.getDefaultMessage(), fieldError.getField()));
    }

    /**
     * 500 Internal Server
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    protected ApiResponseDto<Object> handleException(final Exception e) {
        return ApiResponseDto.error(Error.INTERNAL_SERVER_ERROR);
    }

    /**
     * Business custom error
     */

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ApiResponseDto> handleBusinessException(final BusinessException e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(ApiResponseDto.error(e.getError(), e.getMessage()));
    }
}
