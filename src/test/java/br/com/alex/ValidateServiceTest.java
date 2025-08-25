package br.com.alex;
import br.com.alex.services.ValidateService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidateServiceTest {

    private final ValidateService validateService = new ValidateService();

    @Test
    void deveValidarCpfValido() {
        String cpfValido = "52998224725"; // exemplo válido
        assertTrue(validateService.documentIsValid(cpfValido));
    }

    @Test
    void deveInvalidarCpfInvalido() {
        String cpfInvalido = "12345678900";
        assertFalse(validateService.documentIsValid(cpfInvalido));
    }

    @Test
    void deveValidarCnpjValido() {
        String cnpjValido = "11222333000181"; // exemplo válido
        assertTrue(validateService.documentIsValid(cnpjValido));
    }

    @Test
    void deveInvalidarCnpjInvalido() {
        String cnpjInvalido = "11222333000100";
        assertFalse(validateService.documentIsValid(cnpjInvalido));
    }

    @Test
    void deveLancarExcecaoSeFormatoForInvalido() {
        String documentoInvalido = "123"; // nem CPF nem CNPJ
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            validateService.documentIsValid(documentoInvalido);
        });

        assertEquals("O documento deve conter 11 dígitos (CPF) ou 14 dígitos (CNPJ)", exception.getMessage());
    }
}
