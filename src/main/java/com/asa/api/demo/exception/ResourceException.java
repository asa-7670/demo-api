package com.asa.api.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceException  extends RuntimeException {
    public ResourceException(String message) {
        super(message);
    }
}
