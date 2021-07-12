package br.com.zup.mercadolivre.compra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PagSeguroRequest implements RetornoGatewayPagamento{

	@NotBlank
	private String idTransacao;

	@NotNull
	private StatusRetornoPagSeguro status;

	public PagSeguroRequest(@NotBlank String idTransacao, @NotNull StatusRetornoPagSeguro status) {
		this.idTransacao = idTransacao;
		this.status = status;
	}

	public Transacao toTransacao(Compra compra) {
		return new Transacao(status.normaliza(), idTransacao, compra);
	}

}
