package br.com.alura.spring.data.services;

import br.com.alura.spring.data.modelo.Unidade;
import br.com.alura.spring.data.repository.UnidadeRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudUnidadeService {

    private final UnidadeRepository unidadeRepository;

    public CrudUnidadeService(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    private Boolean system = true;

    public void inicial() {
        while (system) {
            System.out.println("Qual acao de cargo deseja executar");

            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar");
            System.out.println("4 - Deletar");

            System.out.println("6 - Sair");

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
                default:
                    system = false;
                    break;
            }
        }
    }

    public void salvar() {
        System.out.println("ENTRE COM O NOME DA UNIDADE!");
        String nomeUnid = new Scanner(System.in).nextLine();

        Unidade unidade = new Unidade(nomeUnid);
        unidadeRepository.save(unidade);
        System.out.println("UNIDADE SALVA COM SUCESSO!");

    }

    public void relatorio() {
        System.out.println("RELAÇÃO DE UNIDADES CADASTRADAS");

        Iterable<Unidade> uniCad = unidadeRepository.findAll();
        uniCad.forEach(unidades -> System.out.println(unidades));

    }

    public void atualizar() {
        relatorio();
        System.out.println("QUAL UNIDADE DESEJA RENOMEAR?");
        Long posicao = new Scanner(System.in).nextLong();

        System.out.println("DIGITE O NOVO NOME DA UNIDADE!");
        String novoUni = new Scanner(System.in).nextLine();

        Unidade uni = new Unidade(posicao, novoUni);
        unidadeRepository.save(uni);
        System.out.println("UNIDADE ALTERADA COM SUCESSO!");


    }

    public void deletar() {
        System.out.println("QUAL UNIDADE SERA EXCLUIDA:");
        Long del = new Scanner(System.in).nextLong();

        unidadeRepository.deleteById(del);
        System.out.println("UNIDADE EXCLUIDA COM SUCESSO:");


    }
}
