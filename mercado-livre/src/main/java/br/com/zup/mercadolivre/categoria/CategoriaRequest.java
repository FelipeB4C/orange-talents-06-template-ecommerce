package br.com.zup.mercadolivre.categoria;

import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.mercadolivre.compartilhado.UniqueValue;

public class CategoriaRequest {

	@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	@NotBlank
	private String nome;

	@Positive
	private Long categoriaId;

	public Categoria toModel(CategoriaRepository repo){
		Categoria categoria = new Categoria(nome);
		if (categoriaId != null) {
			Categoria categoriaMae = verifyObj(repo);
			categoria.setCategoriaMae(categoriaMae);
		}
		return categoria;
	}

	public Categoria verifyObj(CategoriaRepository repo) {
		Optional<Categoria> categoria = repo.findById(categoriaId);
		return categoria.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não deu certo"));
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

}
