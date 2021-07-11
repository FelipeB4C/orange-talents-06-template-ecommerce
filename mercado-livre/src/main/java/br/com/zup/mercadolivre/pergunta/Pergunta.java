package br.com.zup.mercadolivre.pergunta;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

@Entity
public class Pergunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String titulo;
	
	@ManyToOne
	@NotNull
	private Usuario usuario;
	
	@ManyToOne
	@NotNull
	private Produto produto;

	private LocalDateTime instante = LocalDateTime.now();
	
	public Pergunta(@NotBlank String titulo, @NotNull Usuario usuario, @NotNull Produto produto) {
		this.titulo = titulo;
		this.usuario = usuario;
		this.produto = produto;
	}

	public Usuario getPerguntador() {
		return usuario;
	}

	public Usuario getDonoProduto() {
		return produto.getDono();
	}
	
	
	
}
