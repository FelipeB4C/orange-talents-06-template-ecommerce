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
	
	@Deprecated
	public OpiniaoProduto() {
		
	}

	public OpiniaoProduto(@Min(1) @Max(5) Integer nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao, @NotNull Usuario consumidor, @NotNull Produto produto) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.consumidor = consumidor;
		this.produto = produto;
	}

	
	

	public Object getTitulo() {
		return titulo;
	}

	public Object getDescricao() {
		return descricao;
	}

	public Integer getNota() {
		return nota;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((consumidor == null) ? 0 : consumidor.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OpiniaoProduto other = (OpiniaoProduto) obj;
		if (consumidor == null) {
			if (other.consumidor != null)
				return false;
		} else if (!consumidor.equals(other.consumidor))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
	
	
	
}
