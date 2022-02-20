package br.com.alura.spring.data.services;

import br.com.alura.spring.data.modelo.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudCargoService {

    private Boolean system = true;
    private final CargoRepository cargoRepository;  //injetando dependecia

    Scanner entrada = new Scanner(System.in);
    public CrudCargoService(CargoRepository cargoRepository) {  //constrtutor
        this.cargoRepository = cargoRepository;
    }

    public void inicial(Scanner scanner) {

        while(system) {
            System.out.println("Qual acao de cargo deseja executar");

            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar");
            System.out.println("4 - Deletar");

            System.out.println("6 - Sair");
            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    salvar(scanner);
                    break;
                case 2:
                    atualizar(scanner);
                    break;
                case 3:
                    relatorio();
                    break;
                case 4:
                    deletar(scanner);
                    break;
                default:
                    system = false;
                    break;
            }

        }

    }

    public boolean salvar(Scanner scanner){
        System.out.println("DESCRIÇÃO DO CARGO:");
        String descricao = scanner.nextLine();

        Cargo cargo = new Cargo(descricao);

        cargoRepository.save(cargo);
        System.out.println("! SALVO AO BANCO DE DADOS !");
        return  true;

    }

    public void relatorio(){
        Iterable<Cargo> listar = cargoRepository.findAll();  //alcaçar a coleçao no banco
        listar.forEach(elemento -> System.out.print(elemento));
    }
    public boolean atualizar(Scanner scanner) {
        System.out.print("Qual codigo deseja atualizar?  ");
        Long posicao = scanner.nextLong();

        System.out.print("Novo nome do Cargo?  ");
        String novoCargo = new Scanner(System.in).nextLine();

        Cargo cargo = new Cargo(posicao, novoCargo);
        cargoRepository.save(cargo);

       return true;
    }

    public void deletar(Scanner scanner) {
        System.out.println("Qual Registro deseja Deletar? ");
         Long posicaoDelete = new Scanner(System.in).nextLong();

        cargoRepository.deleteById(posicaoDelete);
        System.out.println("Registro Excluido com sucesso! ");
        relatorio();

    }
}
