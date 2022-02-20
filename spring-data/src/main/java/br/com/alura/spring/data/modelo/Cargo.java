package br.com.alura.spring.data.modelo;

import javax.persistence.*;

@Entity @Table(name = "cargos")
public class Cargo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;

    public Cargo() {    }

    public Cargo(Long id, String descricion) {
        this.id = id;
        this.descricao = descricion;
    }

    public Cargo(long l) {
        this.id = l;
    }

    public Cargo(String descricao) {
        this.descricao =descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Cargos Cadastrados: " +
                "id: " + id + ", descricao: " + descricao + "\n";
    }
}
