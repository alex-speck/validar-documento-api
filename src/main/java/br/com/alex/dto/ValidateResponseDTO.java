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

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getValidatedAt() {
        return validatedAt;
    }

    public void setValidatedAt(LocalDateTime validatedAt) {
        this.validatedAt = validatedAt;
    }
}
