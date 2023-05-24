package com.osu.BackEndToDoApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
//Custom exception for Record not found
public class RecordNotFoundException extends ResponseStatusException {
    public RecordNotFoundException(String reason) {
        super(HttpStatus.NOT_FOUND, reason);
    }
    

}
