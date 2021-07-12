package br.com.zup.mercadolivre.outrossistemas;

import javax.validation.constraints.NotNull;

public class CompraNFRequest {

	@NotNull
	private Long idCompra;
	
	@NotNull
	private Long idComprador;

	public CompraNFRequest(Long idCompra, Long idComprador) {
		this.idCompra = idCompra;
		this.idComprador = idComprador;
	}

}
