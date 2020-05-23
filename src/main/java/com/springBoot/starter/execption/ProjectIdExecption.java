package com.springBoot.starter.execption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectIdExecption extends RuntimeException {

    public ProjectIdExecption(String message) {
        super(message);
    }
}
