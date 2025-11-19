package collector.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionHandler {

    public ErrorResponse handeIllegalArgumentException(final IllegalArgumentException e) {
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.toString())
                .reason("Передан неверный аргумент")
                .message(e.getMessage())
                .timestamp(LocalDateTime.now().toString())
                .build();
    }
}
