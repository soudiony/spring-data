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

    public Funcionario() { }

    public Funcionario(String nome) {
        this.nome = nome;

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
}
