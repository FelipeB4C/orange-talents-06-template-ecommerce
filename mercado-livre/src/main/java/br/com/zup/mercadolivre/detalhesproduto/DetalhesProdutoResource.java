package br.com.zup.mercadolivre.detalhesproduto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.produto.ProdutoRepository;

@RestController
@RequestMapping("/detalhes")
public class DetalhesProdutoResource {
	
	
	@Autowired
	private ProdutoRepository repo;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> details(@PathVariable Long id){
		Produto produto = repo.findById(id).get();
		DetalhesResponse detalhes = new DetalhesResponse(produto);
		return ResponseEntity.ok().body(detalhes);
	}
	
}
