package br.com.alura.spring.data.services;

import br.com.alura.spring.data.modelo.Cargo;
import br.com.alura.spring.data.modelo.Funcionario;
import br.com.alura.spring.data.modelo.FuncionarioDto;
import br.com.alura.spring.data.modelo.Unidade;
import br.com.alura.spring.data.repository.CargoRepository;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.repository.UnidadeRepository;
import br.com.alura.spring.data.specification.SpecificationFuncionario;
import net.bytebuddy.asm.Advice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class CrudFuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final CrudUnidadeService crudUnidadeService;
    private final CrudCargoService crudCargoService;
    private final RelatorioService relatorioService;

    Scanner entrada = new Scanner(System.in);

    //como sera passado formato da data.
    DateTimeFormatter formatarData = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, UnidadeRepository unidadeRepository, CargoRepository cargoRepository, CrudUnidadeService crudUnidadeService, CrudCargoService crudCargoService, RelatorioService relatorioService) {
        this.funcionarioRepository = funcionarioRepository;
        this.crudUnidadeService = crudUnidadeService;
        this.crudCargoService = crudCargoService;
        this.relatorioService = relatorioService;
    }

    private Boolean system = true;

    public void inicial() {
        while (system) {
            System.out.println("Qual acao de cargo deseja executar");

            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar");
            System.out.println("4 - Deletar");
            System.out.println("5 - Buscar por Maior Salário");
            System.out.println("6 - Buscar por Nome");
            System.out.println("7 - Buscar por Data");
            System.out.println("8 - Buscar por Nome - Specification");

            System.out.println("9 - Sair");

            int action = new Scanner(System.in).nextInt();

            switch (action) {
                case 1:
                    salvar();
                    break;
                case 2:
                    atualizar();
                    break;
                case 3:
                    relatorio();
                    break;
                case 4:
                    deletar();
                    break;
                case 5:
                    funcionarioSalario();
                    break;
                case 6:
                    buscarNome();
                    break;
                case 7:
                    buscaPorData();
                    break;
                case 8:
                    buscaPorNomeSpecification();
                    break;

                default:
                    system = false;
                    break;
            }
        }
    }




  
    public void funcionarioSalario() {

        List<FuncionarioDto> listar = funcionarioRepository.findByMaiorSalarioPorNomeEID();
        listar.forEach(x -> System.out.println(
                "Funcionario: " + x.getId() + " | Salario: " + x.getSalario() + " | Nome; " + x.getNome()));
    }

    private void buscarNome() {
        relatorioService.inicial();
    }

    public void buscaPorData() {
        System.out.println("QUAL A DATA DE CONTRATAÇÃO! DD/MM/AAAA");
        String data = new Scanner(System.in).next();

        LocalDate dataConvertida = LocalDate.parse(data, formatarData); //com objeto formatarData passando Mascara
        List<Funcionario> porData = funcionarioRepository.findByData(dataConvertida);

        porData.forEach(elements -> System.out.println(elements));

    }

    public void buscarPorMaiorSalario() {
        System.out.println("QUAL O NOME DESEJA BUSCAR!");
        String nome = new Scanner(System.in).nextLine();

        System.out.println("QUAL SALARIO DESEJA BUSCAR!");
        BigDecimal salario = new Scanner(System.in).nextBigDecimal();

        // System.out.println("QUAL A DATA DE CONTRATAÇÃO!");
        //    String data = new Scanner(System.in).nextLine();
        //   LocalDate dataConvertida = LocalDate.parse(data, formatarData ); //com objeto formatarData passando Mascara

        List<Funcionario> listaMaioresSalario = funcionarioRepository.findByNomeSalarioMaior(nome, salario);
        listaMaioresSalario.forEach(salarioMaior -> System.out.println(salarioMaior));
        //busca qualquer funcionario  que tenha nome passado com data de contracao igual, com salario maior que o passado


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

        System.out.println("VALOR DO SALÁRIO DO FUNCIONARIO: ");
        BigDecimal salario = new Scanner(System.in).nextBigDecimal();

        Funcionario funcionario = new Funcionario(nomeFunc, cpfFunc, salario, uni, cargo);

        funcionarioRepository.save(funcionario);
        System.out.println("Funcionario Cadastrado com sucesso!");
    }

    public Long totalDeFuncionario() {
        Pageable pageable = PageRequest.of(1, 1, Sort.by(Sort.Direction.ASC, "nome")); //CRIANDO PAGINACAO, "nome" = atrtibuto de funcionario
        Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
        return funcionarios.getTotalElements();

    }

    public void relatorio() {

        System.out.println("HA UM TOTAL DE: " + totalDeFuncionario() + " FUNCIONÁRIOS CADASTRADOS!");

        System.out.println("QUANTAS PÁGINAS VC DESEJA VISUALIZAR");   //PAGINAÇÃO
        int pag = new Scanner(System.in).nextInt();

        // Iterable<Funcionario> all = funcionarioRepository.findAll(pageable); nao mais entrega Iterable

        //QUAL PAGINA / NUMERO POR PAGINA / ORDENAÇÃO Sort.unsorted (PADRAO)  /  Sort.by(Sort.Direction.ASC) (ORDEM CRESCENTE)
        Pageable pageable = PageRequest.of(pag, 5, Sort.by(Sort.Direction.ASC, "nome")); //CRIANDO PAGINACAO, "nome" = atrtibuto de funcionario

        Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);  //findAll reconhece o Pageable como parametro, respeitando os filtros criando via PageRequest
        System.out.println(funcionarios);
        System.out.println("Pagina Atual: " + funcionarios.getNumber());  //mostra a pagina que o cliente ESTA no MOMENTO.
        System.out.println("TOTAL DE ELEMENTOS: : " + funcionarios.getTotalElements());  //mostra o numero total de elemntos .

        funcionarios.forEach(elementos -> System.out.println(elementos));

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
