package br.com.alex.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ValidateService {

    public boolean cpfIsValid(String cpf) {
        if (cpf == null || cpf.length() != 11) return false;

        // converter para lista de inteiros
        List<Integer> cpfList = cpf.chars()
                .map(Character::getNumericValue)
                .boxed()
                .collect(Collectors.toList());

        // validar cpf com todos dígitos iguais
        boolean allEqual = cpfList.stream().allMatch(d -> d.equals(cpfList.get(0)));
        if (allEqual) return false;

        // validar dígitos verificadores
        int primeiroDigito = calcularDigito(cpfList, 9);
        int segundoDigito = calcularDigito(cpfList, 10);

        return primeiroDigito == cpfList.get(9) && segundoDigito == cpfList.get(10);
    }

    private int calcularDigito(List<Integer> cpfList, int posicao) {
        int soma = 0;
        int multiplicador = 2;

        for (int i = posicao - 1; i >= 0; i--) {
            soma += cpfList.get(i) * multiplicador;
            multiplicador++;
        }

        int resultado = (soma * 10) % 11;
        return resultado == 10 ? 0 : resultado;
    }

}
