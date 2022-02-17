package br.com.alura.spring.data.services;

import br.com.alura.spring.data.modelo.Cargo;
import br.com.alura.spring.data.modelo.Funcionario;
import br.com.alura.spring.data.modelo.Unidade;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeRepository;
import org.springframework.stereotype.Service;

import javax.security.sasl.SaslClient;
import java.math.BigDecimal;
import java.util.Scanner;

@Service
public class CrudFuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final CrudUnidadeService crudUnidadeService;
    private final CrudCargoService crudCargoService;

    Scanner entrada = new Scanner(System.in);

    public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, UnidadeRepository unidadeRepository, CargoRepository cargoRepository, CrudUnidadeService crudUnidadeService, CrudCargoService crudCargoService) {
        this.funcionarioRepository = funcionarioRepository;
        this.crudUnidadeService = crudUnidadeService;
        this.crudCargoService = crudCargoService;
    }


    public void salvar() {
        System.out.println("ENTRE COM OS DADOS DO FUNIONÁRIO!");
        System.out.print("NOME: ");
        String nomeFunc = new Scanner(System.in).nextLine();
        System.out.print("CPF: ");
        String cpfFunc = new Scanner(System.in).nextLine();


        System.out.println("LISTA DE CARGOS");
        crudCargoService.relatorio();
        System.out.println("DIGITE O COD: DO NOME DO CARGO!");
        Long nomeCargo = new Scanner(System.in).nextLong();
        Cargo cargo = new Cargo(nomeCargo);

        System.out.println("LISTA DE UNIDADES");
        crudUnidadeService.relatorio();
        System.out.println("DIGITE O COD: DA UNIDADE!");
        Long nomeUni = new Scanner(System.in).nextLong();
        Unidade uni = new Unidade(nomeUni);

        System.out.println("VALOR DO SALÁRIO DO FUNCIONARIO: " );
        BigDecimal salario = new Scanner(System.in).nextBigDecimal();

        Funcionario funcionario = new Funcionario(nomeFunc, cpfFunc, salario, uni, cargo);

        funcionarioRepository.save(funcionario);
        System.out.println("Funcionario Cadastrado com sucesso!");
    }

    public void relatorio() {
        System.out.println("LISTA DE FUNCIONARIOS!");
        Iterable<Funcionario> all = funcionarioRepository.findAll();
        all.forEach(elementos -> System.out.println(elementos));
    }

    public void atualizar() {
        Scanner entradaFunc = new Scanner(System.in);

        System.out.println("QUAL FUNCIONARIO DESEJA ALTERAR");
        Long codFunc = entrada.nextLong();

        System.out.println("ALTERE O NOME: ");
        String novoNome = entradaFunc.nextLine();


        Funcionario funcionario = new Funcionario(codFunc, novoNome);
        funcionarioRepository.save(funcionario);

    }

    public void deletar() {
        System.out.println("QUAL FUNCIONARIO DESEJA EXCLUIR");
        Long idFunc = entrada.nextLong();

        funcionarioRepository.deleteById(idFunc);
        System.out.println("EXCLUIDO COM SUCESSO");
        relatorio();
    }
}
