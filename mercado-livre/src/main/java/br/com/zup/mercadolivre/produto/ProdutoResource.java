package br.com.zup.mercadolivre.produto;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.mercadolivre.login.TokenService;
import br.com.zup.mercadolivre.usuario.Usuario;

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

	@Autowired
	private UploaderFake uploaderFake;

	@InitBinder(value = "ProdutoRequest")
	public void init(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new ProibeCaracteristicaComNomeRepetidoValidator());
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Void> insert(@RequestBody @Valid ProdutoRequest obj) {
		Long usuarioId = obj.usuarioId(httpRequest, tokenService);
		Produto produto = obj.toModel(manager, usuarioId);
		produtoRepository.save(produto);
		return ResponseEntity.created(null).build();
	}

	@PostMapping("/{id}/imagens")
	@Transactional
	public ResponseEntity<?> insertImages(@PathVariable("id") Long id, @Valid ImagensRequest request) {

		Long usuarioId = request.usuarioId(httpRequest, tokenService);
		Usuario usuario = manager.find(Usuario.class, usuarioId);
		Produto produto = produtoRepository.findById(id).get();

		if (!produto.pertenceAoUsuario(usuario)) {

			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}

		Set<String> links = uploaderFake.envia(request.getImagens());
		produto.associaImagens(links);
		produtoRepository.save(produto);
		return ResponseEntity.created(null).build();
	}

}
