package br.com.alura.lojavirtual.testes;

import br.com.alura.lojavirtual.dao.CategoriaDAO;
import br.com.alura.lojavirtual.dao.ClienteDAO;
import br.com.alura.lojavirtual.dao.ProdutoDAO;
import br.com.alura.lojavirtual.modelo.*;
import br.com.alura.lojavirtual.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastrarProdutoComHeranca {
	
	public static void main(String[] args) {
		
		popularBanco();
	
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(em);

		List<Produto> produtos = produtoDAO.buscarTodos();
		produtos.forEach(p -> System.out.println(p.getNome()));

		em.close();
		
	}

	private static void popularBanco() {

		Categoria livros = new Categoria("Livros");
		Categoria informatica = new Categoria("Informática");

		Livro livro = new Livro("Entendendo Algoritmos", "Um guia ilustrado para programadores e outros curiosos", new BigDecimal("39.90"), livros, "Aditya Y. Bhargava", 264);
		Informatica pendrive = new Informatica("Pendrive", "Armazenamento 8GB", new BigDecimal("29.90"), informatica, "Sansdisk", "Cruzer Blade");

		EntityManager em = JPAUtil.getEntityManager();

		CategoriaDAO categoriaDAO = new CategoriaDAO(em);
		ProdutoDAO produtoDAO = new ProdutoDAO(em);

		em.getTransaction().begin();

		categoriaDAO.cadastrar(livros);
		categoriaDAO.cadastrar(informatica);

		produtoDAO.cadastrar(livro);
		produtoDAO.cadastrar(pendrive);

		em.getTransaction().commit();
		em.close();
	}

}
