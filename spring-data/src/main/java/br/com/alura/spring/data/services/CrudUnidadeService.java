package br.com.alura.spring.data.services;

import br.com.alura.spring.data.repository.UnidadeRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudUnidadeService {

    Scanner entrada = new Scanner(System.in);
    private final UnidadeRepository unidadeRepository;

    public CrudUnidadeService(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

}
