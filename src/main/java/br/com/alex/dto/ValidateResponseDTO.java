package br.com.alex.dto;

import java.time.LocalDateTime;

public class ValidateResponseDTO {

    private boolean valid;
    private String message;
    private LocalDateTime validatedAt;

    public ValidateResponseDTO(boolean valid, String message, LocalDateTime validatedAt) {
        this.valid = valid;
        this.message = message;
        this.validatedAt = validatedAt;
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
