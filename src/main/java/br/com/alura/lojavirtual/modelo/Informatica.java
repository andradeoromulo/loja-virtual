package br.com.alura.lojavirtual.modelo;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Informatica extends Produto {

    String marca;
    String modelo;

    public Informatica() {}

    public Informatica(String nome, String descricao, BigDecimal preco, Categoria categoria, String marca, String modelo) {
        super(nome, descricao, preco, categoria);
        this.marca = marca;
        this.modelo = modelo;
    }
}
