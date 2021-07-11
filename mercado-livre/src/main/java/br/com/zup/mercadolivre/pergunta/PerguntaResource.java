package br.com.zup.mercadolivre.pergunta;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.login.TokenService;


@RestController
@RequestMapping("/perguntas")
public class PerguntaResource {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private TokenService service;
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PerguntaRepository perguntaRepo;
	
	@Autowired
	private Emails emails;
	
	@PostMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> insert(@PathVariable("id") Long id, @RequestBody @Valid PerguntaRequest obj){
		
		Long usuarioId = obj.usuarioId(request, service);
		Pergunta pergunta = obj.toModel(manager, id, usuarioId);
		perguntaRepo.save(pergunta);
		
		
		emails.novaPergunta(pergunta);
		
		return ResponseEntity.created(null).build();
	}
	
}
