package com.github.navelogic.api.Exception;

import com.github.navelogic.api.DTO.ErroResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponseDTO> handleValidationErrors(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> String.format("%s: %s", error.getField(), error.getDefaultMessage()))
                .toList();

        return ResponseEntity.badRequest().body(
                ErroResponseDTO.builder()
                        .message("Erro de validação")
                        .details(errors)
                        .status(400)
                        .build()
        );
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErroResponseDTO> handleValidationException(ValidationException ex) {
        return ResponseEntity.badRequest().body(
                ErroResponseDTO.builder()
                        .message(ex.getMessage())
                        .status(400)
                        .build()
        );
    }

}
