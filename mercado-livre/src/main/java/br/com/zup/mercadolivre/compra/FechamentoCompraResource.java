package br.com.zup.mercadolivre.compra;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/retorno")
public class FechamentoCompraResource {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private EventoNovaCompra eventosNovaCompra;

	@PostMapping("/pagseguro/{id}")
	@Transactional
	public String processamentoPagSeguro(@PathVariable("id") Long id, @Valid PagSeguroRequest obj) {

		return processa(id, obj);
	}

	@PostMapping("/paypal/{id}")
	@Transactional
	public String processamentoPayPal(@PathVariable("id") Long id, @Valid PayPalRequest obj) {
		return processa(id, obj);

	}

	private String processa(Long id, RetornoGatewayPagamento retornoPagamento) {
		Compra compra = manager.find(Compra.class, id);
		compra.adicionaTransacao(retornoPagamento);

		manager.merge(compra);
		eventosNovaCompra.processa(compra);

		return "compra retorno";
	}

}
