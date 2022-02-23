package br.com.alura.spring.data.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity @Table(name = "funcionarios")

public class Funcionario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private BigDecimal salario;
    private LocalDate dataEntrada = LocalDate.now();

    @ManyToOne
    private Cargo cargo;

    @ManyToOne
    private Unidade unidade;

    public Funcionario() {

    }

    public Funcionario(String novoNome, BigDecimal salario) {
        this.nome = novoNome;
        this.salario = salario;

    }


    public Funcionario(String nomeFunc, String cpf, BigDecimal salario, Unidade uni, Cargo cargo) {
       this.nome = nomeFunc;
       this.cpf = cpf;
       this.salario = salario;
       this.unidade = uni;
       this.cargo = cargo;
    }

    public Funcionario(Long codFunc, String novoNome) {
       this.id = codFunc;
       this.nome = novoNome;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public Cargo getCargo() {
        return cargo;
    }

    @Override
    public String toString() {
        return  "id:" + id +
                "  | nome:'" + nome + '\'' +
                "  | cpf:'" + cpf + '\'' +
                "  | salario:" + salario +
                "  | dataEntrada:" + dataEntrada +
                "  | cargo:" + cargo;


    }
}
