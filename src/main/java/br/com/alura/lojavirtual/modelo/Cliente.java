package br.com.alura.lojavirtual.modelo;

import javax.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private DadosPessoais dadosPessoais;
	
	public Cliente() {}
	
	public Cliente(String nome, String cpf) {
		this.dadosPessoais = new DadosPessoais(nome, cpf);
	}

	public DadosPessoais getDadosPessoais() {
		return dadosPessoais;
	}
}
