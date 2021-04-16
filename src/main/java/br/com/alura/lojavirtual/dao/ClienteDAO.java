package br.com.alura.lojavirtual.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.lojavirtual.modelo.Cliente;
import br.com.alura.lojavirtual.modelo.Pedido;
import br.com.alura.lojavirtual.modelo.Produto;

public class ClienteDAO {

	private EntityManager em;
	
	public ClienteDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Cliente cliente) {
		this.em.persist(cliente);
	}
	
	public Cliente buscarPorId(Long id) {
		return this.em.find(Cliente.class, id);
	}
		
}
