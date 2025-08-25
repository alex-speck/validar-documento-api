package br.com.alex.dto;

import java.time.LocalDateTime;

public class ValidateResponseDTO {

    private String document;
    private boolean valid;
    private String message;
    private LocalDateTime validatedAt;

    public ValidateResponseDTO(String document, boolean valid, String message, LocalDateTime validatedAt) {
        this.document = document;
        this.valid = valid;
        this.message = message;
        this.validatedAt = validatedAt;
    }
}
