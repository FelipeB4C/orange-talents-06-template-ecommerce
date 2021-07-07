package br.com.zup.mercadolivre.usuario;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioRepository repo;

	@PostMapping
	@Transactional
	public ResponseEntity<Void> insert(@RequestBody @Valid UsuarioRequest obj) {
		Usuario usuario = obj.toModel();
		repo.save(usuario);
		return ResponseEntity.created(null).build();
	}

}
