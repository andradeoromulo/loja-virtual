package br.com.alura.lojavirtual.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.lojavirtual.modelo.Produto;

public class ProdutoDAO {

	private EntityManager em;
	
	public ProdutoDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}
	
	public void atualizar(Produto produto) {
		this.em.merge(produto);
	}
	
	public void remover(Produto produto) {
		produto = em.merge(produto);
		this.em.remove(produto);
	}
	
	public Produto buscarPorId(Long id) {
		return this.em.find(Produto.class, id);
	}
	
	public List<Produto> buscarTodos() {
		String jpql = "SELECT p FROM Produto p";
		return em.createQuery(jpql, Produto.class).getResultList();
	}
	
	public List<Produto> buscarPorNome(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome"; // Esse é o named parameter, mas poderia ser passado por número tbm: ?1
		return em.createQuery(jpql, Produto.class)
				.setParameter("nome", nome) // Se fosse por número, aqui pegaríamos pelo número: 1
				.getResultList();
	}
	
	public List<Produto> buscarPorCategoria(String nome) {
		String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = ?1"; // O JOIN é feito automaticamento pois o hibernate já sabe que categoria é um outro objeto
		return em.createQuery(jpql, Produto.class)
				.setParameter(1, nome)
				.getResultList();
	}
	
}
