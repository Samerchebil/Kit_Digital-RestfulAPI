package com.example.kit_digital.Exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiException {
private final String message;
private final Throwable throwable;
private final HttpStatus httpStatus;
private final ZonedDateTime zonedDateTime;
public ApiException(String message, Throwable throwable, HttpStatus httpStatus, ZonedDateTime zonedDateTime) {
this.message = message;
this.throwable = throwable;
this.httpStatus = httpStatus;
this.zonedDateTime = zonedDateTime;
}
public String getMessage() {return message;}
public HttpStatus getHttpStatus() {return httpStatus;}
public Throwable getThrowable() {return throwable;}
}
