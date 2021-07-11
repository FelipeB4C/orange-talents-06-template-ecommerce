package br.com.zup.mercadolivre.pergunta;

import java.time.LocalDateTime;
import java.util.Objects;

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
public class Pergunta implements Comparable<Pergunta>{

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
	
	public Pergunta() {
		
	}
	
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

	@Override
	public int hashCode() {
		return Objects.hash(produto, titulo, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pergunta other = (Pergunta) obj;
		return Objects.equals(produto, other.produto) && Objects.equals(titulo, other.titulo)
				&& Objects.equals(usuario, other.usuario);
	}

	@Override
	public int compareTo(Pergunta o) {
		return this.titulo.compareTo(o.titulo);
	}

	public String getTitulo() {
		return titulo;
	}
	
	
	
}
