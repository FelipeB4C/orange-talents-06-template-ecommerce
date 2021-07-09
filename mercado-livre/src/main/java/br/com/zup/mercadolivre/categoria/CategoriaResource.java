package br.com.zup.mercadolivre.categoria;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	
	@Autowired
	private CategoriaRepository repo;

	
	@PostMapping
	@Transactional
	public ResponseEntity<Void> insert(@RequestBody @Valid CategoriaRequest obj){
		Categoria categoria = obj.toModel(repo);
		repo.save(categoria);
		return ResponseEntity.created(null).build();
	}
	
}
