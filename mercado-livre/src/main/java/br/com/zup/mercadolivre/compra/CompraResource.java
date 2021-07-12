package br.com.zup.mercadolivre.compra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.mercadolivre.login.TokenService;
import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

@RestController
@RequestMapping("/compras")
public class CompraResource {

	@Autowired
	private HttpServletRequest httpRequest;

	@Autowired
	private TokenService tokenService;

	@PersistenceContext
	private EntityManager manager;

	@PostMapping
	@Transactional
	public ResponseEntity<?> insert(@RequestBody @Valid CompraRequest obj, UriComponentsBuilder uriComponentsBuilder)
			throws BindException {
		Long comprador = obj.usuarioId(httpRequest, tokenService);
		Usuario usuario = manager.find(Usuario.class, comprador);
		Produto produto = manager.find(Produto.class, obj.getProdutoId());

		int quantidade = obj.getQuantidade();
		boolean abateu = produto.abataEstoque(obj.getQuantidade());
		if (abateu) {
			GatewayPagamento gateway = obj.getGateway();
			Compra novaCompra = new Compra(produto, quantidade, usuario, gateway);
			manager.persist(novaCompra);

			if (gateway.equals(GatewayPagamento.pagseguro)) {

				String urlRetornoPgSeguro = uriComponentsBuilder.path("/retorno-pagseguro/{id}")
						.buildAndExpand(novaCompra.getId()).toString();

				String link = "pagseguro.com/" + novaCompra.getId() + "?redirectUrl="+urlRetornoPgSeguro;
				return ResponseEntity.status(302).body(link);
			} else {
				String urlRetornoPaypal = uriComponentsBuilder.path("/retorno-paypal/{id}")
						.buildAndExpand(novaCompra.getId()).toString();

				String link = "paypal.com/" + novaCompra.getId() + "?redirectUrl="+urlRetornoPaypal;
				return ResponseEntity.status(302).body(link);
			}
		}

		BindException problemaComEstoque = new BindException(obj, "CompraRequest");
		problemaComEstoque.reject(null, "Não foi possível realizar compra por conta do estoque");

		throw problemaComEstoque;
	}

}
