package br.com.zup.mercadolivre.produto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zup.mercadolivre.usuario.Usuario;

@Entity
public class OpiniaoProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Min(1)
	@Max(5)
	private Integer nota;
	
	@NotBlank
	private String titulo;
	
	@Column(length = 500)
	@NotBlank
	@Size(max = 500)
	private String descricao;
	
	@ManyToOne
	@NotNull
	private Usuario consumidor;
	
	@ManyToOne
	@NotNull
	private Produto produto;

	public OpiniaoProduto(@Min(1) @Max(5) Integer nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao, @NotNull Usuario consumidor, @NotNull Produto produto) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.consumidor = consumidor;
		this.produto = produto;
	}
	
	
	
}
