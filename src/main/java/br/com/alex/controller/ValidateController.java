package br.com.alex.controller;

import br.com.alex.dto.ValidateResponseDTO;
import br.com.alex.services.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.ZoneId;


@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/validar")
public class ValidateController {

    @Autowired
    private ValidateService validateService;

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ValidateResponseDTO> validarCpf(@PathVariable("cpf") String cpf) {
        if (validateService.cpfIsValid(cpf)) {
            return ResponseEntity.ok(new ValidateResponseDTO(true, "Cpf informado é valido!", LocalDateTime.now(ZoneId.of("UTC-3"))));
        }
        return ResponseEntity.badRequest().body(new ValidateResponseDTO(false, "Cpf informado é inválido!", LocalDateTime.now(ZoneId.of("UTC-3"))));
    }

}
