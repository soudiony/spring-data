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

    public Unidade(String nomeUnid) {
        this.descricao = nomeUnid;
    }

    public Unidade(long l) {
        this.id = l;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Unidade(Long posicao, String novoUni) {
        this.id = posicao;
        this.descricao = novoUni;
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

    @Override
    public String toString() {
        return "Unidade{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", Endereco='" + Endereco + '\'' +
                '}';
    }
}
