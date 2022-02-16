package br.com.alura.spring.data.modelo;

import javax.persistence.*;

@Entity @Table(name = "unidade")
public class Unidade {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private String Endereco;

    public Unidade(String descricao, String endereco) {
        this.descricao = descricao;
        Endereco = endereco;
    }

    public Unidade(){

    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEndereco() {
        return Endereco;
    }
}
