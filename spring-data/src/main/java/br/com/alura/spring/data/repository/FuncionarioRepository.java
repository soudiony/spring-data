package br.com.alura.spring.data.repository;

import br.com.alura.spring.data.modelo.Funcionario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Long> {
    //passado um nome retorna uma lista de funcionario, findByAtributo

    //  List<Funcionario> findByNome(String procNome);
    List<Funcionario> findByNomeStartingWith(String nome);

    //Por Nome Extenso..
    // List<Funcionario> findByNomeAndSalarioGreaterThanAndDataEntrada(String nome, BigDecimal salario, LocalDate data);

    //Por Nome JPQL..                       //p.nome (nome é um atributo de Funcionario | :nomeX é um Parametro Passado)
    @Query("SELECT p FROM Funcionario p WHERE p.nome = :nomeX And p.salario >= :salarioX And p.dataEntrada = :dataX")
    List<Funcionario> findNomeSalarioMaiorDataContratacao(String nomeX, BigDecimal salarioX, LocalDate dataX);
}