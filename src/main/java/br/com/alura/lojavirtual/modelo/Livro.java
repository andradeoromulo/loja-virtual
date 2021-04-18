package br.com.alura.lojavirtual.modelo;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Livro extends Produto {

    private String autor;
    private int numeroDePaginas;

    public Livro() {

    }

    public Livro(String nome, String descricao, BigDecimal preco, Categoria categoria, String autor, int numeroDePaginas) {
        super(nome, descricao, preco, categoria);
        this.autor = autor;
        this.numeroDePaginas = numeroDePaginas;
    }
}
