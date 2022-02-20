package br.com.alura.spring.data.services;

import br.com.alura.spring.data.modelo.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioService {

    private final FuncionarioRepository funcionarioRepository;


    public RelatorioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }
        private Boolean system = true;
    
        public void inicial() {
            while (system) {
                System.out.println("Qual acao de cargo deseja executar");

                System.out.println("1 - Buscar");
                System.out.println("2 - Voltar");

                System.out.println("3 - Sair");
                int action = new Scanner(System.in).nextInt();

                switch (action) {
                    case 1:
                        buscar();
                        break;
                    
                    default:
                        system = false;
                        break;
                }
            }
           }

    private void buscar() {

        System.out.print("Qual o nome do Funcionário: ");
        List<Funcionario> list = funcionarioRepository.findByNomeStartingWith(new Scanner(System.in).nextLine());
        list.forEach(func -> System.out.println(func));

        //  List<Funcionario> list = funcionarioRepository.findByNome(new Scanner(System.in).nextLine());

        // System.out.println("O Funcionário esta cadastrado: " + list);


    }


}
