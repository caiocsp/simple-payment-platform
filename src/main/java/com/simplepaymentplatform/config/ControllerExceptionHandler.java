package com.simplepaymentplatform.config;

import com.simplepaymentplatform.dto.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity handleDuplicatedEntryException(DataIntegrityViolationException exception) {
        ExceptionDTO dto = new ExceptionDTO("Usuário já cadastrado!", "400");
        return ResponseEntity.badRequest().body(dto);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleEntityNotFoundException(EntityNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleGenericException(Exception exception) {
        ExceptionDTO dto = new ExceptionDTO(exception.getMessage(), "500");
        return ResponseEntity.badRequest().body(dto);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity handleResponseStatusException(ResponseStatusException exception) {
        ExceptionDTO dto = new ExceptionDTO(exception.getReason(), String.valueOf(exception.getStatusCode().value()));
        return ResponseEntity.badRequest().body(dto);
    }
}
