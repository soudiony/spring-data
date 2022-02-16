package br.com.alura.spring.data;

import br.com.alura.spring.data.services.CrudCargoService;
import br.com.alura.spring.data.services.CrudFuncionarioService;
import br.com.alura.spring.data.services.CrudUnidadeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

    private boolean temMaisCad = true;

    private final CrudCargoService crudCargoService;  //classe responsavel por salvar
    private final CrudFuncionarioService crudFuncionarioService;
    private final CrudUnidadeService crudUnidadeService;



    public SpringDataApplication(CrudCargoService crudCargoService, CrudFuncionarioService crudFuncionarioService, CrudUnidadeService crudUnidadeService) {
        this.crudCargoService = crudCargoService;
        this.crudFuncionarioService = crudFuncionarioService;
        this.crudUnidadeService = crudUnidadeService;
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args); }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (temMaisCad) {
            System.out.println("------------ O QUE DESEJA REALIZAR: ------------");
            System.out.println("1 - 'CADASTRAR CARGO: ");
            System.out.println("2 - 'ATUALIZAR CARGO: ");
            System.out.println("3 - 'RELATORIO DE CARGOS: ");
            System.out.println("4 - 'DELETAR CARGO: ");

            System.out.println("5 - 'CADASTRAR FUNCIONARIO: ");
            System.out.println("6 - 'ATUALIZAR FUNCIONARIO: ");
            System.out.println("7 - 'RELATORIO FUNCIONARIO: ");
            System.out.println("8 - 'DELETAR FUNCIONARIO: ");

            System.out.println("9 - 'CADASTRAR UNIDADE: ");
            System.out.println("10 - 'ATUALIZAR UNIDADE: ");
            System.out.println("11 - 'RELATORIO DE UNIDADE: ");
            System.out.println("12 - 'DELETAR UNIDADE: ");

            System.out.println("13 - 'SAIR : ");
            System.out.println("------------ --------------------- ------------");


            int opcaoInt = scanner.nextInt();
            //String opcao = scanner.next().toUpperCase();

            switch (opcaoInt) {
                case 1: crudCargoService.salvar(); break;
                case 2: crudCargoService.atualizar(); break;
                case 3: crudCargoService.relatorio(); break;
                case 4: crudCargoService.deletar(); break;

                case 5: crudFuncionarioService.salvar(); break;
             //   case 6: crudFuncionarioService.atualizar(); break;
           //     case 7: crudFuncionarioService.relatorio(); break;
             //   case 8: crudFuncionarioService.deletar(); break;

            //    case 9:  crudUnidadeService.salvar(); break;
           //     case 10: crudUnidadeService.atualizar(); break;
            //    case 11: crudUnidadeService.relatorio(); break;
             //   case 12: crudUnidadeService.deletar(); break;

                default:
                    temMaisCad = false;

            }
        }



    }

}
