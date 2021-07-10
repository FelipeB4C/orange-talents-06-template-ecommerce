package br.com.zup.mercadolivre.produto;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

@Entity
public class ImagemProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne
	@Valid
	private Produto produto;

	@NotNull
	@URL
	private String link;

	@Deprecated
	public ImagemProduto() {

	}

	public ImagemProduto(Produto produto, String link) {
		this.produto = produto;
		this.link = link;
	}

	@Override
	public int hashCode() {
		return Objects.hash(link, produto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImagemProduto other = (ImagemProduto) obj;
		return Objects.equals(link, other.link) && Objects.equals(produto, other.produto);
	}

}
