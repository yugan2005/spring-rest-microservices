package com.home.restmicroservices.exceptions;

import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestMicroserviceResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResponseException> handleAllExceptions(Exception ex, WebRequest request) {
    ResponseException responseException = ResponseException.builder()
        .timeStamp(new Date())
        .message(ex.getMessage())
        .description(request.getDescription(false))
        .build();
    return new ResponseEntity<>(responseException, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(NoUserFoundException.class)
  public ResponseEntity<ResponseException> handleNoSuchUserException(NoUserFoundException ex, WebRequest request) {
    ResponseException responseException = ResponseException.builder()
        .timeStamp(new Date())
        .message(ex.getMessage())
        .description(request.getDescription(false))
        .build();
    return new ResponseEntity<>(responseException, HttpStatus.NOT_FOUND);
  }

  @Override
  public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    ResponseException responseException = ResponseException.builder().timeStamp(new Date()).
        message("argument is invalid").
        description(ex.getBindingResult().getAllErrors().stream().
            map(ObjectError::getDefaultMessage).
            collect(Collectors.joining("; "))).
        build();
    return new ResponseEntity<>(responseException, HttpStatus.BAD_REQUEST);
  }
}
