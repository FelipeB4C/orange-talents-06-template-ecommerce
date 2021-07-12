package br.com.zup.mercadolivre.compra;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.mercadolivre.login.TokenService;

public class CompraRequest {

	@Positive
	private int quantidade;

	@NotNull
	private Long produtoId;
	
	@NotNull
	private GatewayPagamento gateway;

	public CompraRequest(@Positive int quantidade, @NotNull Long produtoId, GatewayPagamento gateway) {
		this.quantidade = quantidade;
		this.produtoId = produtoId;
		this.gateway = gateway;
	}
	
	public Long usuarioId(HttpServletRequest request, TokenService service) {
		String token = request.getHeader("Authorization");
		token = token.substring(7, token.length());
		Long usuarioId = service.getIdUsuario(token);
		return usuarioId;
	}

	
	public int getQuantidade() {
		return quantidade;
	}

	public Long getProdutoId() {
		return produtoId;
	}
	
	public GatewayPagamento getGateway() {
		return gateway;
	}
	
}
