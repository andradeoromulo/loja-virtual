package br.com.alura.lojavirtual.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.lojavirtual.dao.CategoriaDAO;
import br.com.alura.lojavirtual.dao.ProdutoDAO;
import br.com.alura.lojavirtual.modelo.Categoria;
import br.com.alura.lojavirtual.modelo.Produto;
import br.com.alura.lojavirtual.util.JPAUtil;

public class CadastrarProduto {
	
	public static void main(String[] args) {
		
		Categoria celularesCategoria = new Categoria("Celulares");
		Produto celular = new Produto("Redmi Note 7", "Armazenamento 32GB", new BigDecimal("1200"), celularesCategoria);
		
		EntityManager em = JPAUtil.getEntityManager();
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		
		em.getTransaction().begin();
		categoriaDAO.cadastrar(celularesCategoria);
		produtoDAO.cadastrar(celular);
		em.getTransaction().commit();
		em.close();
		
	}

}
