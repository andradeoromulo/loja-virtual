package br.com.alura.lojavirtual.testes;

import br.com.alura.lojavirtual.dao.CategoriaDAO;
import br.com.alura.lojavirtual.dao.ClienteDAO;
import br.com.alura.lojavirtual.dao.PedidoDAO;
import br.com.alura.lojavirtual.dao.ProdutoDAO;
import br.com.alura.lojavirtual.modelo.*;
import br.com.alura.lojavirtual.util.JPAUtil;
import br.com.alura.lojavirtual.vo.RelatorioDeVendasVO;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class TesteParametrosDinamicos {

	public static void main(String[] args) {
		
		popularBanco();
		
		EntityManager em = JPAUtil.getEntityManager();

		ProdutoDAO produtoDAO = new ProdutoDAO(em);

		System.out.println("--- Parâmetros dinâmicos JPQL ---");
		List<Produto> produtos = produtoDAO.buscarPorParametros("Entendendo Algoritmos", null, null);
		produtos.forEach(produto -> System.out.println("Produto: " + produto.getNome()));

		System.out.println("--- Parâmetros dinâmicos Criteria ---");
		produtos = produtoDAO.buscarPorParametrosCriteria(null, new BigDecimal("29.90"), null);
		produtos.forEach(produto -> System.out.println("Produto: " + produto.getNome()));

	}
	
	private static void popularBanco() {
	
		Categoria celulares = new Categoria("Celulares");
		Categoria livros = new Categoria("Livros");
		Categoria informatica = new Categoria("Informática");

		Produto celular = new Produto("Redmi Note 7", "Armazenamento 32GB", new BigDecimal("1200"), celulares);
		Produto livro = new Produto("Entendendo Algoritmos", "Autor: Aditya Y. Bhargava", new BigDecimal("29.90"), livros);
		Produto pendrive = new Produto("Pendrive Sansdisk", "Armazenamento 8GB", new BigDecimal("29.90"), informatica);
		
		EntityManager em = JPAUtil.getEntityManager();
		
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		
		em.getTransaction().begin();
		
		categoriaDAO.cadastrar(celulares);
		categoriaDAO.cadastrar(livros);
		categoriaDAO.cadastrar(informatica);

		produtoDAO.cadastrar(celular);
		produtoDAO.cadastrar(livro);
		produtoDAO.cadastrar(pendrive);
		
		em.getTransaction().commit();
		em.close();
	}

}
