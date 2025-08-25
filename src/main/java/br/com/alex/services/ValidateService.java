package br.com.alex.services;

import br.com.alex.dto.ValidateResponseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ValidateService {

    public ValidateResponseDTO documentIsValid (String document) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC-3"));

        if (document == null || document.length() != 11 && document.length() != 14) {
            throw new IllegalArgumentException("O documento deve conter 11 dígitos (CPF) ou 14 dígitos (CNPJ)");
        }

        // converter para lista de inteiros
        List<Integer> documentList = document.chars()
                .map(Character::getNumericValue)
                .boxed()
                .collect(Collectors.toList());

        // validar documento com todos dígitos iguais
        boolean allEqual = documentList.stream().allMatch(d -> d.equals(documentList.get(0)));
        if (allEqual) {
            return new ValidateResponseDTO(
                    document,
                    false,
                    "O documento informado possui todos os dígitos iguais",
                    now
            );
        }

        // se for do tamanho de cpf usa metodo cpfIsValid para verificar validade
        boolean isValid;
        if (documentList.size() == 11) {
            isValid = cpfIsValid(documentList);
            return new ValidateResponseDTO(document, isValid,
                    isValid ? "CPF válido" : "CPF inválido", now);
        } else if (documentList.size() == 14) {
            isValid = cnpjIsValid(documentList);
            return new ValidateResponseDTO(document, isValid,
                    isValid ? "CNPJ válido" : "CNPJ inválido", now);
        }


        // fallback
        throw new IllegalArgumentException("O documento deve conter 11 dígitos (CPF) ou 14 dígitos (CNPJ)");

    }

    private boolean cpfIsValid(List<Integer> cpfList) {

        // validar dígitos verificadores
        int primeiroDigito = calcularDigitoCpf(cpfList, 9);
        int segundoDigito = calcularDigitoCpf(cpfList, 10);

        return primeiroDigito == cpfList.get(9) && segundoDigito == cpfList.get(10);
    }

    private int calcularDigitoCpf(List<Integer> cpfList, int posicao) {
        int soma = 0;
        int multiplicador = 2;

        for (int i = posicao - 1; i >= 0; i--) {
            soma += cpfList.get(i) * multiplicador;
            multiplicador++;
        }

        int resultado = (soma * 10) % 11;
        return resultado == 10 ? 0 : resultado;
    }

    private boolean cnpjIsValid(List<Integer> cnpjList) {

        // validar digitos verificadores
        int primeiroDigito = calcularDigitoCnpj(cnpjList, 12);
        int segundoDigito = calcularDigitoCnpj(cnpjList, 13);

        return primeiroDigito == cnpjList.get(12) && segundoDigito == cnpjList.get(13);
    }

    private int calcularDigitoCnpj (List<Integer> cnpjList, int posicao) {
        int[] peso1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] peso2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        if (posicao == 12) {
            int soma = 0;


            for (int i = 0; i < 12; i++) {
                soma += cnpjList.get(i) * peso1[i];
            }

            if (soma % 11 <= 1) return  0;
            return 11 - (soma % 11);
        }

        if (posicao == 13) {
            int soma = 0;


            for (int i = 0; i < 13; i++) {
                soma += cnpjList.get(i) * peso2[i];
            }

            if (soma % 11 <= 1) return  0;
            return 11 - (soma % 11);
        }

        throw new IllegalArgumentException("Posição inválida: " + posicao);
    }

}
