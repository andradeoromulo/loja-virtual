package br.com.alura.lojavirtual.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.lojavirtual.modelo.Pedido;
import br.com.alura.lojavirtual.modelo.Produto;

public class PedidoDAO {

	private EntityManager em;
	
	public PedidoDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}
		
}
