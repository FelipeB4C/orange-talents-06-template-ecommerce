package br.com.zup.mercadolivre.compra;

public interface RetornoGatewayPagamento {

	Transacao toTransacao(Compra compra);
	
}
