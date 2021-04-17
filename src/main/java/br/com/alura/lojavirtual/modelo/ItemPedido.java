package br.com.alura.lojavirtual.modelo;

import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table(name = "itens_pedido")
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "preco_unitario")
	private BigDecimal precoUnitario;
	
	private int quantidade;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Pedido pedido;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Produto produto;

	public ItemPedido() {
	
	}

	public ItemPedido(int quantidade, Pedido pedido, Produto produto) {
		this.quantidade = quantidade;
		this.pedido = pedido;
		this.produto = produto;
		this.precoUnitario = produto.getPreco();
	}
	
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public BigDecimal getValor() {
		return precoUnitario.multiply(new BigDecimal(quantidade));
	}
}
