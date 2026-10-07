package com.user.service.UserService.exception;
import com.user.service.UserService.payload.ApiRespons;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiRespons> resourceNotFoundexception(ResourceNotFoundException ex)
    {
        String message = ex.getMessage();
        ApiRespons response = ApiRespons.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<ApiRespons>(response,HttpStatus.NOT_FOUND);
    }
}
