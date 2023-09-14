package com.sandystack.exp.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@AllArgsConstructor
public class RestException extends RuntimeException {

    private final HttpStatus httpStatus;

    private final List<String> details;

    public RestException(String message, Throwable cause, HttpStatus httpStatus, List<String> details) {
        super(message, cause);
        this.httpStatus = httpStatus;
        this.details = details;
    }

    public static RestExceptionBuilder builder() {
        return new RestExceptionBuilder();
    }

    public static class RestExceptionBuilder {
        private HttpStatus httpStatus;
        private List<String> details;
        private String message;
        private Throwable cause;

        RestExceptionBuilder() {
        }

        public RestExceptionBuilder httpStatus(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
            return this;
        }

        public RestExceptionBuilder details(List<String> details) {
            this.details = details;
            return this;
        }

        public RestExceptionBuilder message(String message) {
            this.message = message;
            return this;
        }

        public RestExceptionBuilder cause(Throwable cause) {
            this.cause = cause;
            return this;
        }


        public RestException build() {
            return new RestException(this.message, this.cause, this.httpStatus,  this.details);
        }

        public String toString() {
            return "RestException.RestExceptionBuilder(httpStatus=" + this.httpStatus + ", details=" + this.details + ")";
        }
    }
}
