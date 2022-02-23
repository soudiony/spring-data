package br.com.alura.spring.data.services;

import br.com.alura.spring.data.modelo.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.specification.SpecificationFuncionario;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioFuncionarioDinamico {

    private final FuncionarioRepository funcionarioRepository;
    private final DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;

    }

    public void inicial() {
        System.out.print("POR FAVOR DIGITE O NOME A BUSCAR: ");
        String nomeDigitado = new Scanner(System.in).next();
        if (nomeDigitado.equalsIgnoreCase("NULL")) {
            nomeDigitado = null;
        }

        System.out.print("POR FAVOR DIGITE O CPF A BUSCAR: ");
        String cpfDigitado = new Scanner(System.in).next();
        if (cpfDigitado.equalsIgnoreCase("NULL")) {
            cpfDigitado = null;
        }

        System.out.print("POR FAVOR DIGITE O SALARIO BASE A BUSCAR: ");
        BigDecimal salarioDigitado = new Scanner(System.in).nextBigDecimal();
        if (salarioDigitado.equals("NULL")) {
            salarioDigitado = null;
        }

        System.out.print("POR FAVOR DIGITE UMA DATA BASE A BUSCAR: ");
        String dataDigitada = new Scanner(System.in).next();

        LocalDate dataConvertida;

        if (dataDigitada.equalsIgnoreCase("NULL")) {
            dataConvertida = null;
        } else {
            dataConvertida = LocalDate.parse(dataDigitada, formatoData);

        }

        List<Funcionario> funcionarios =
                funcionarioRepository.findAll(Specification.where(
                        SpecificationFuncionario.nome(nomeDigitado).or(
                                SpecificationFuncionario.cpf(cpfDigitado)).or(
                                SpecificationFuncionario.salario(salarioDigitado).or(
                                        SpecificationFuncionario.dataContratacao(dataConvertida)))));

        funcionarios.forEach(x -> System.out.println(x));

    }
}
