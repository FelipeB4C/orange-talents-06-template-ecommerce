package br.com.zup.mercadolivre.compra;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

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

	@OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
	private Set<Transacao> transacoes = new HashSet<>();

	@Enumerated
	@NotNull
	private GatewayPagamento gatewayPagamento;

	@Deprecated
	public Compra() {

	}

	public Compra(Produto produto, int quantidade, Usuario usuario, GatewayPagamento gatewayPagamento) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.usuario = usuario;
		this.gatewayPagamento = gatewayPagamento;
	}

	public Long getId() {
		return id;
	}
	

	public Usuario getDonoProduto() {
		return produto.getDono();
	}

	public Usuario getComprador() {
		return usuario;
	}

	
	public void adicionaTransacao(@Valid RetornoGatewayPagamento obj) {
		Transacao novaTransacao = obj.toTransacao(this);
		Assert.isTrue(!this.transacoes.contains(novaTransacao),
				"Já existe uma transação igual a essa processada: " + novaTransacao);

		Assert.isTrue(transacoesConcluidasComSucesso().isEmpty(), "Essa compra já foi concluída com sucesso");

		this.transacoes.add(novaTransacao);
	}

	private Set<Transacao> transacoesConcluidasComSucesso() {
		Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream().filter(Transacao::concluidaComSucesso)
				.collect(Collectors.toSet());
		Assert.isTrue(transacoesConcluidasComSucesso.size() <= 1, "Tem mais de uma transação concluída com sucesso nessa compra: "+this.id);
		return transacoesConcluidasComSucesso;
	}

	public boolean processadaComSucesso() {
		return !transacoesConcluidasComSucesso().isEmpty();
	}
}
