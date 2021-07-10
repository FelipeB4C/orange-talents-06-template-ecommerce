package br.com.zup.mercadolivre.produto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.login.TokenService;

@RestController
@RequestMapping("/opinioes")
public class OpiniaoProdutoResource {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private TokenService service;
	
	@Autowired
	private OpiniaoProdutoRepository opniaoProdutoRepository;
	
	@PostMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> insert(@PathVariable("id") Long id, @RequestBody OpiniaoProdutoRequest obj){
		Long usuarioId = obj.usuarioId(request, service);
		OpiniaoProduto opniao = obj.toModel(manager, usuarioId, id);
		opniaoProdutoRepository.save(opniao);
		return ResponseEntity.created(null).build();
	}
	
}
