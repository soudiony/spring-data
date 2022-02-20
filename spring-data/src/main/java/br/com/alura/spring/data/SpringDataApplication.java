package br.com.alura.spring.data;

import br.com.alura.spring.data.services.CrudCargoService;
import br.com.alura.spring.data.services.CrudFuncionarioService;
import br.com.alura.spring.data.services.CrudUnidadeService;
import br.com.alura.spring.data.services.RelatorioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

    private boolean system = true;

    private final CrudCargoService crudCargoService;  //classe responsavel por salvar
    private final CrudFuncionarioService crudFuncionarioService;
    private final CrudUnidadeService crudUnidadeService;
    private final RelatorioService relatorioService;


    public SpringDataApplication(CrudCargoService crudCargoService, CrudFuncionarioService crudFuncionarioService, CrudUnidadeService crudUnidadeService, RelatorioService relatorioService) {
        this.crudCargoService = crudCargoService;
        this.crudFuncionarioService = crudFuncionarioService;
        this.crudUnidadeService = crudUnidadeService;
        this.relatorioService = relatorioService;
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
      while (system) {
            System.out.println("Qual função deseja executar?");

            System.out.println("1 - Funcionario");
            System.out.println("2 - Cargo");
            System.out.println("3 - Unidade");

            System.out.println("4 - Sair");

            int function = new Scanner(System.in).nextInt();

            switch (function) {
                case 1:
                    crudFuncionarioService.inicial();
                    break;
                case 2:
                    crudCargoService.inicial(new Scanner(System.in));
                    break;
                case 3:
                    crudUnidadeService.inicial();
                    break;
                default:
                    System.out.println("Finalizando");
                    system = false;
            }
        }
    }
}
