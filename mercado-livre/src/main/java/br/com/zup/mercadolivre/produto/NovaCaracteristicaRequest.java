package br.com.zup.mercadolivre.produto;

import javax.validation.constraints.NotBlank;

public class NovaCaracteristicaRequest {

	@NotBlank
	private String nome;

	@NotBlank
	private String descricao;

	public NovaCaracteristicaRequest(@NotBlank String nome, @NotBlank String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public CaracteristicaProduto toModel(Produto produto) {
		return new CaracteristicaProduto(nome, descricao, produto);
	}

}
