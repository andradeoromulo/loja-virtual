package br.com.alura.lojavirtual.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.lojavirtual.dao.CategoriaDAO;
import br.com.alura.lojavirtual.dao.ClienteDAO;
import br.com.alura.lojavirtual.dao.PedidoDAO;
import br.com.alura.lojavirtual.dao.ProdutoDAO;
import br.com.alura.lojavirtual.modelo.Categoria;
import br.com.alura.lojavirtual.modelo.Cliente;
import br.com.alura.lojavirtual.modelo.ItemPedido;
import br.com.alura.lojavirtual.modelo.Pedido;
import br.com.alura.lojavirtual.modelo.Produto;
import br.com.alura.lojavirtual.util.JPAUtil;

public class CadastroDePedido {

	public static void main(String[] args) {
		
		popularBanco();
		
		EntityManager em = JPAUtil.getEntityManager();
		
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		Produto produto = produtoDAO.buscarPorId(1l);
		
		ClienteDAO clienteDAO = new ClienteDAO(em);
		Cliente cliente = clienteDAO.buscarPorId(1l);
		
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(2, pedido, produto));
		
		em.getTransaction().begin();	
		
		PedidoDAO pedidoDAO = new PedidoDAO(em);
		pedidoDAO.cadastrar(pedido);
		
		em.getTransaction().commit();


	}
	
	private static void popularBanco() {
	
		Categoria celularesCategoria = new Categoria("Celulares");
		Produto celular = new Produto("Redmi Note 7", "Armazenamento 32GB", new BigDecimal("1200"), celularesCategoria);
		Cliente cliente = new Cliente("Romualdo", "12345678909");
		
		EntityManager em = JPAUtil.getEntityManager();
		
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		ClienteDAO clienteDAO = new ClienteDAO(em);
		
		em.getTransaction().begin();
		
		categoriaDAO.cadastrar(celularesCategoria);
		produtoDAO.cadastrar(celular);
		clienteDAO.cadastrar(cliente);
		
		em.getTransaction().commit();
		em.close();
	}

}
