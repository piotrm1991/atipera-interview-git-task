package restrepo.exception;

import restrepo.response.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * GlobalExceptionHandler provides centralized handling of exceptions in the application.
 * It uses Spring's RestControllerAdvice annotation to intercept and handle exceptions thrown from
 * various controller methods.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Handles EntityNotFoundException, indicating that a requested entity was not found.
   *
   * @param ex The EntityNotFoundException that was thrown.
   * @return A ResponseEntity with a not found status and an error message.
   */
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ExceptionResponse> handleEntityNotFoundException(
      EntityNotFoundException ex) {
    log.error("{} status: {}", ex.getMessage(), HttpStatus.NOT_FOUND.value());

    return new ResponseEntity<>(
        new ExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()),
        HttpStatus.NOT_FOUND);
  }

  /**
   * Handles all other unhandled exceptions that might occur in the application.
   *
   * @param ex The exception that was thrown.
   * @return A ResponseEntity with an internal server error status and an error message.
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionResponse> handleAllExceptions(
      Exception ex) {
    log.error("{} status: {}", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());

    return new ResponseEntity<>(
        new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
