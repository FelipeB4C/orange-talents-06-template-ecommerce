package br.com.zup.mercadolivre.compra;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@NotNull
	@Valid
	private Produto produto;
	@Positive
	@NotNull
	private int quantidade;
	@ManyToOne
	@NotNull
	@Valid
	private Usuario usuario;
	
	@Enumerated
	@NotNull
	private GatewayPagamento gatewayPagamento;
	
	public Compra(Produto produto, int quantidade, Usuario usuario, GatewayPagamento gatewayPagamento) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.usuario = usuario;
		this.gatewayPagamento = gatewayPagamento;
	}
	
	public Long getId() {
		return id;
	}
}
