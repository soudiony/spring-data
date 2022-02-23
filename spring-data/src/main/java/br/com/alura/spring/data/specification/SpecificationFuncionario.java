package br.com.alura.spring.data.specification;


import br.com.alura.spring.data.modelo.Funcionario;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SpecificationFuncionario {
                                             //baseado no atributo a validar, no caso: nome
    public static Specification<Funcionario> nome(String nomeX){
        return (root, criteriaQuery, criteriaBuilder) ->   //Variaveis criadas(r,c,c)
                criteriaBuilder.like(root.get("nome"), "%" + nomeX + "%" ) ;  //pegue criteriaBuilder faça operação like% %, com root e atributo da entidade Funcionario, "like" % + nome + %
    }

    public static Specification<Funcionario> cpf(String cpfX){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("cpf"),  cpfX );
    }

    public static Specification<Funcionario> salario(BigDecimal salarioX){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("salario"), salarioX );}

    public static Specification<Funcionario> dataContratacao(LocalDate dataX){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("dataEntrada"), dataX );}

}
