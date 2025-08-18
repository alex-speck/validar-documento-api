package br.com.alex.controller;

import br.com.alex.services.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/validar")
public class ValidateController {

    @Autowired
    private ValidateService validateService;

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<String> validarCpf(@PathVariable("cpf") String cpf) {
        if (validateService.cpfIsValid(cpf)) {
            return ResponseEntity.ok("Cpf informado é valido!");
        }
        return ResponseEntity.badRequest().body("CPF inválido!");
    }

}
