package br.com.alura.lojavirtual.testes;

import java.math.BigDecimal;
import java.util.List;

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
import br.com.alura.lojavirtual.vo.RelatorioDeVendasVO;

public class CadastroDePedido {

	public static void main(String[] args) {
		
		popularBanco();
		
		EntityManager em = JPAUtil.getEntityManager();
		
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		Produto celular = produtoDAO.buscarPorId(1l);
		Produto livro = produtoDAO.buscarPorId(2l);
		Produto pendrive = produtoDAO.buscarPorId(3l);
		
		ClienteDAO clienteDAO = new ClienteDAO(em);
		Cliente cliente = clienteDAO.buscarPorId(1l);
		
		Pedido pedido1 = new Pedido(cliente);
		pedido1.adicionarItem(new ItemPedido(2, pedido1, celular));
		pedido1.adicionarItem(new ItemPedido(4, pedido1, pendrive));

		Pedido pedido2 = new Pedido(cliente);
		pedido2.adicionarItem(new ItemPedido(10, pedido2, livro));
		pedido2.adicionarItem(new ItemPedido(2, pedido2, pendrive));
		
		em.getTransaction().begin();	
		
		PedidoDAO pedidoDAO = new PedidoDAO(em);
		pedidoDAO.cadastrar(pedido1);
		pedidoDAO.cadastrar(pedido2);
		
		em.getTransaction().commit();

		BigDecimal totalVendido = pedidoDAO.calcularTotalVendido();
		System.out.println("Total vendido: R$" + totalVendido);

		List<RelatorioDeVendasVO> relatorioDeVendas = pedidoDAO.gerarRelatorioVendas();
		relatorioDeVendas.forEach(System.out::println);

	}
	
	private static void popularBanco() {
	
		Categoria celulares = new Categoria("Celulares");
		Categoria livros = new Categoria("Livros");
		Categoria informatica = new Categoria("Informática");

		Produto celular = new Produto("Redmi Note 7", "Armazenamento 32GB", new BigDecimal("1200"), celulares);
		Produto livro = new Produto("Entendendo Algoritmos", "Autor: Aditya Y. Bhargava", new BigDecimal("39.90"), livros);
		Produto pendrive = new Produto("Pendrive Sansdisk", "Armazenamento 8GB", new BigDecimal("29.90"), informatica);

		Cliente cliente = new Cliente("Romualdo", "12345678909");
		
		EntityManager em = JPAUtil.getEntityManager();
		
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		ClienteDAO clienteDAO = new ClienteDAO(em);
		
		em.getTransaction().begin();
		
		categoriaDAO.cadastrar(celulares);
		categoriaDAO.cadastrar(livros);
		categoriaDAO.cadastrar(informatica);

		produtoDAO.cadastrar(celular);
		produtoDAO.cadastrar(livro);
		produtoDAO.cadastrar(pendrive);

		clienteDAO.cadastrar(cliente);
		
		em.getTransaction().commit();
		em.close();
	}

}
