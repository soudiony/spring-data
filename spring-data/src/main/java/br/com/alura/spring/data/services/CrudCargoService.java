package br.com.alura.spring.data.services;

import br.com.alura.spring.data.modelo.Cargo;
import br.com.alura.spring.data.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudCargoService {

    private final CargoRepository cargoRepository;  //injetando dependecia
    Scanner entrada = new Scanner(System.in);

    public CrudCargoService(CargoRepository cargoRepository) {  //constrtutor
        this.cargoRepository = cargoRepository;
    }

    public void relatorio(){
        Iterable<Cargo> listar = cargoRepository.findAll();  //alcaçar a coleçao no banco
        listar.forEach(elemento -> System.out.print(elemento));
    }
    public boolean atualizar() {
        System.out.print("Qual codigo deseja atualizar?  ");
        Scanner scanner = new Scanner(System.in);
        Long posicao = scanner.nextLong();

        System.out.print("Novo nome do Cargo?  ");
        scanner = new Scanner(System.in);
        String novoCargo = scanner.nextLine();

        Cargo cargo = new Cargo(posicao, novoCargo);
        cargoRepository.save(cargo);

       return true;
    }

    public boolean salvar(){
        System.out.println("DESCRIÇÃO DO CARGO:");
        Scanner scanner = new Scanner(System.in);

        String descricao = scanner.nextLine();
        Cargo cargo = new Cargo(descricao);

        cargoRepository.save(cargo);
        System.out.println("! SALVO AO BANCO DE DADOS !");
        return  true;

    }

    public void deletar() {
        System.out.println("Qual Registro deseja Deletar? ");
        entrada = new Scanner(System.in);
        Long posicaoDelete = entrada.nextLong();

        cargoRepository.deleteById(posicaoDelete);
        System.out.println("Registro Excluido com sucesso! ");
        relatorio();


    }
}
