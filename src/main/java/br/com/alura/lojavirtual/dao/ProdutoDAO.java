package br.com.alura.lojavirtual.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

	// Parâmetros dinâmicos com JPQL
	public List<Produto> buscarPorParametros(String nome, BigDecimal preco, LocalDate dataCadastro) {
		String jpql = "SELECT p FROM Produto p WHERE 1=1";

		if(nome != null && !nome.trim().isEmpty())
			jpql += " AND p.nome = :nome";
		if(preco != null)
			jpql += " AND p.preco = :preco";
		if(dataCadastro != null)
			jpql += " AND p.dataCadastro = :dataCadastro";

		TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);

		if(nome != null && !nome.trim().isEmpty())
			query.setParameter("nome", nome);
		if(preco != null)
			query.setParameter("preco", preco);
		if(dataCadastro != null)
			query.setParameter("dataCadastro", dataCadastro);

		return query.getResultList();
	}

	// Parâmetros dinâmicos com Criteria
	public List<Produto> buscarPorParametrosCriteria(String nome, BigDecimal preco, LocalDate dataCadastro) {

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = criteriaBuilder.createQuery(Produto.class);
		Root<Produto> from = query.from(Produto.class);

		Predicate filters = criteriaBuilder.and();

		if(nome != null && !nome.trim().isEmpty())
			filters = criteriaBuilder.and(filters, criteriaBuilder.equal(from.get("nome"), nome));
		if(preco != null)
			filters = criteriaBuilder.and(filters, criteriaBuilder.equal(from.get("preco"), preco));
		if(dataCadastro != null)
			filters = criteriaBuilder.and(filters, criteriaBuilder.equal(from.get("dataCadastro"), dataCadastro));

		query.where(filters);

		return em.createQuery(query).getResultList();

	}
	
}
