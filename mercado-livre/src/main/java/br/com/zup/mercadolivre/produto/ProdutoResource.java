package br.com.zup.mercadolivre.produto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.login.TokenService;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	@Autowired
	private HttpServletRequest httpRequest;
	
	@Autowired
	private TokenService tokenService;
	
	@PersistenceContext
	private EntityManager manager; 
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@InitBinder
	public void init(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new ProibeCaracteristicaComNomeRepetidoValidator());
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<Void> insert(@RequestBody @Valid ProdutoRequest obj){
		Long usuarioId = obj.usuarioId(httpRequest, tokenService);
		Produto produto = obj.toModel(manager, usuarioId);
		produtoRepository.save(produto);
		return ResponseEntity.created(null).build();
	}

}
