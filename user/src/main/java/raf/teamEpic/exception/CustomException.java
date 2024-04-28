package raf.teamEpic.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{
    private ErrorCode errorCode;
    private HttpStatus httpStatus;

    public CustomException(String message, ErrorCode errorCode, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }
}
