package com.sandystack.exp.Exception;

import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
@AllArgsConstructor
public class ErrorRequestInterceptor extends ResponseEntityExceptionHandler {

    private static final String CONSTRAINT_VIOLATION_EXCEPTION = "'%s' - '%s'";
    private static final String VALIDATION_ERROR_MESSAGE = "Validation error";

    @ExceptionHandler(value = RestException.class)
    protected ResponseEntity<Object> handleRestError(RestException ex, WebRequest request) {
        return createErrorResponseAndLog(ex.getHttpStatus(), ex, ex.getMessage(), Collections.emptyList(), request);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        List<String> details = ex.getConstraintViolations()
                .stream()
                .map(v -> String.format(CONSTRAINT_VIOLATION_EXCEPTION, v.getPropertyPath(), v.getMessage()))
                .toList();

        return createErrorResponseAndLog(HttpStatus.BAD_REQUEST, ex, VALIDATION_ERROR_MESSAGE, details, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        return createErrorResponseAndLog(status, ex, VALIDATION_ERROR_MESSAGE, extractValidationErrorDetails(ex.getBindingResult()), request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String element = String.format("Missing Parameter %s (%s)", ex.getParameterName(), ex.getParameterType());
        List<String> details = List.of(element);
        return createErrorResponseAndLog(status, ex, VALIDATION_ERROR_MESSAGE, details, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> details = List.of(String.format("Type mismatch, property %s, required type %s", ex.getPropertyName(), ex.getRequiredType()));
        return createErrorResponseAndLog(status, ex, VALIDATION_ERROR_MESSAGE, details, request);
    }

    private ResponseEntity<Object> createErrorResponseAndLog(HttpStatusCode statusCode, Throwable e, String message, List<String> details, WebRequest request) {

        String finalMessage = message != null ? message : e.getMessage();

        log.error(finalMessage, e);  // FIXME: always prints stacktrace

        HttpStatus httpStatus = HttpStatus.resolve(statusCode.value());
        String statusName = Objects.requireNonNull(httpStatus).name();

        String eventTime = LocalDateTime.now(ZoneOffset.UTC).toString();
        ErrorDTO errorDTO = new ErrorDTO(statusCode.value(), statusName, message, eventTime, details);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(errorDTO, headers, httpStatus);
    }

    private List<String> extractValidationErrorDetails(BindingResult bindingResult) {
        return bindingResult.getFieldErrors()
                .stream()
                .map(error -> String.format("%s: %s", error.getObjectName(), error.getDefaultMessage()))
                .toList();
    }
}
