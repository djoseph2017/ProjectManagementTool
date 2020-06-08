package com.springBoot.starter.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProjectValidationService {

    public ResponseEntity<?> validateProject(BindingResult result) {

        if (result.hasErrors()) {
            Map<String, String> errorsMap = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                String field = error.getField();
                String errorDefaultMessage = error.getDefaultMessage();
                errorsMap.put(field, errorDefaultMessage);
            }
            return new ResponseEntity<Map<String, String>>(errorsMap, HttpStatus.BAD_REQUEST);
        }
        return null;
    }

}
