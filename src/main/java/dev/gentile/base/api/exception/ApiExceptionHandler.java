package dev.gentile.base.api.exception;

import dev.gentile.base.business.exception.BusinessConstraintException;
import dev.gentile.base.business.exception.BusinessNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler
{
  @ExceptionHandler({ Exception.class, RuntimeException.class })
  public ResponseEntity<?> exception(Exception e)
  {
    HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    log.error("HTTP Status {} - {}: {}", httpStatus.value(), httpStatus.getReasonPhrase(), e.getMessage());
    return new ResponseEntity<>(new ApiErrorResponse(httpStatus, e.getMessage()), httpStatus);
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<?> httpRequestMethodNotSupportedException(Exception e)
  {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    log.error("HTTP Status {} - {}: {}", httpStatus.value(), httpStatus.getReasonPhrase(), e.getMessage());
    return new ResponseEntity<>(new ApiErrorResponse(httpStatus, e.getMessage()), httpStatus);
  }

  @ExceptionHandler(BusinessNotFoundException.class)
  public ResponseEntity<?> businessNotFoundException(Exception e)
  {
    HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    log.error("HTTP Status {} - {}: {}", httpStatus.value(), httpStatus.getReasonPhrase(), e.getMessage());
    return new ResponseEntity<>(new ApiErrorResponse(httpStatus, e.getMessage()), httpStatus);
  }

  @ExceptionHandler(BusinessConstraintException.class)
  public ResponseEntity<?> businessConstraintException(Exception e)
  {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    log.error("HTTP Status {} - {}: {}", httpStatus.value(), httpStatus.getReasonPhrase(), e.getMessage());
    return new ResponseEntity<>(new ApiErrorResponse(httpStatus, e.getMessage()), httpStatus);
  }
}