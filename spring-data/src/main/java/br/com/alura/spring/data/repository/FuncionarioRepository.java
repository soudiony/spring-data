package br.com.alura.spring.data.repository;

import br.com.alura.spring.data.modelo.Funcionario;
import br.com.alura.spring.data.modelo.FuncionarioDto;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository                                    //FZ paginação e colocar codigo na @Service    //poder de fazer Specification
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Long>, JpaSpecificationExecutor<Funcionario> {
    //passado um nome retorna uma lista de funcionario, findByAtributo

    //  List<Funcionario> findByNome(String procNome);
    List<Funcionario> findByNomeStartingWith(String nome);

    //Por Nome Extenso..
    // List<Funcionario> findByNomeAndSalarioGreaterThanAndDataEntrada(String nome, BigDecimal salario, LocalDate data);

    //Por Nome JPQL.. (usa o nome da Classe  //p.nome (nome é um atributo de Funcionario | :nomeX é um Parametro Passado)
    @Query("SELECT p FROM Funcionario p WHERE p.nome = :nomeX And p.salario >= :salarioX")
    List<Funcionario> findByNomeSalarioMaior(String nomeX, BigDecimal salarioX);

    //Por Query Nativa (usa o nome da tabela do BD e VALUE)..
    @Query(value = "SELECT * FROM funcionarios p WHERE p.data_entrada >= :dateX", nativeQuery = true)
    List<Funcionario> findByData(LocalDate dateX);

    //Interface based Projection: criar Interface PROJETADA com metodos como getID, getSalario, getNome que serão os atributos a passar
    @Query(value = "SELECT p.id, p.nome , p.salario FROM funcionarios p ", nativeQuery = true)
    List<FuncionarioDto> findByMaiorSalarioPorNomeEID();
}

    //usando classe DTO ao inves de Interface Projecao
    //List<FuncionarioDto> findFuncionarioSalarioComProjecaoClasse()
    // classe FuncionarioDto... com atributos a passar e get e set e constrtutor

    //PASSANDO UMA PESQUISA NAO PADRAO PASSANDO O PARAMETRO PAGEABLE
    //List<Funcionario> findByNome(String nome, Pageable pageable);


