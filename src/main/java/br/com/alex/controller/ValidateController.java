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

    @GetMapping("/{documento}")
    public ResponseEntity<ValidateResponseDTO> validarCpf(@PathVariable("documento") String documento) {
        try{
            return ResponseEntity.ok(validateService.documentIsValid(documento));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(new ValidateResponseDTO(documento, false, e.getMessage(), LocalDateTime.now(ZoneId.of("UTC-3"))));
        }
    }

}
