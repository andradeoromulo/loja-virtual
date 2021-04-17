package br.com.alura.lojavirtual.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.lojavirtual.modelo.Pedido;
import br.com.alura.lojavirtual.modelo.Produto;
import br.com.alura.lojavirtual.vo.RelatorioDeVendasVO;

public class PedidoDAO {

	private EntityManager em;
	
	public PedidoDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}

	public Pedido buscarPedidoComCliente(Long id) {
		String jpql = "SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id";
		return em.createQuery(jpql, Pedido.class).getSingleResult();
	}

	public BigDecimal calcularTotalVendido() {
		String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return em.createQuery(jpql, BigDecimal.class).getSingleResult();
	}

	public List<RelatorioDeVendasVO> gerarRelatorioVendas() {
		String jpql = "SELECT new br.com.alura.lojavirtual.vo.RelatorioDeVendasVO" +
				"(produto.nome, SUM(item.quantidade), MAX(pedido.data)) " +
				"FROM Pedido pedido JOIN pedido.itens item JOIN item.produto produto " +
				"GROUP BY produto.nome ";

		return em.createQuery(jpql, RelatorioDeVendasVO.class).getResultList();
	}

}
