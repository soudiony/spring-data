package br.com.alura.spring.data.services;

import br.com.alura.spring.data.modelo.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudFuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    Scanner entrada = new Scanner(System.in);

    public CrudFuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository; }


    public void salvar() {
        System.out.println("ENTRE COM O NOME DO FUNIONÁRIO!");
        String nomeFunc = entrada.nextLine();
        Funcionario funcionario = new Funcionario(nomeFunc);

        funcionarioRepository.save(funcionario);
        System.out.println("Funcionario Cadastrado com sucesso!");
    }
}
