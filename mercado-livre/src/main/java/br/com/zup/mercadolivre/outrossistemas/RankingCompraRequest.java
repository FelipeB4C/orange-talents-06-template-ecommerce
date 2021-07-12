package br.com.zup.mercadolivre.outrossistemas;

import javax.validation.constraints.NotNull;

public class RankingCompraRequest {

	@NotNull
	private Long idCompra;
	
	@NotNull
	private Long idDonoProduto;

	public RankingCompraRequest(Long idCompra, Long idDonoProduto) {
		this.idCompra = idCompra;
		this.idDonoProduto = idDonoProduto;
	}

}
