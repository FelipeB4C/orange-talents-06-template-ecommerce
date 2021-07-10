package br.com.zup.mercadolivre.produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import br.com.zup.mercadolivre.categoria.Categoria;
import br.com.zup.mercadolivre.compartilhado.ExistId;
import br.com.zup.mercadolivre.login.TokenService;
import br.com.zup.mercadolivre.usuario.Usuario;

public class ProdutoRequest {

	@NotBlank
	private String nome;

	@Positive
	@NotNull
	private BigDecimal valor;

	@NotNull
	@PositiveOrZero
	private Integer qtdDisponivel;

	@Size(min = 3)
	@Valid
	private List<NovaCaracteristicaRequest> caracteristicas = new ArrayList<>();

	@NotBlank
	@Size(max = 1000)
	private String descricao;

	@NotNull
	@ExistId(domainClass = Categoria.class, fieldName = "id")
	private Long categoriaId;

	public ProdutoRequest(@NotBlank String nome, @Positive @NotNull BigDecimal valor,
			@NotNull @PositiveOrZero Integer qtdDisponivel, @NotBlank @Size(max = 1000) String descricao,
			@NotNull Long categoriaId, List<NovaCaracteristicaRequest> caracteristicas) {
		this.nome = nome;
		this.valor = valor;
		this.qtdDisponivel = qtdDisponivel;
		this.descricao = descricao;
		this.categoriaId = categoriaId;
		this.caracteristicas.addAll(caracteristicas);
	}

	public Produto toModel(EntityManager manager, Long usuarioId) {
		Categoria categoria = manager.find(Categoria.class, categoriaId);
		Usuario usuario = manager.find(Usuario.class, usuarioId);
		return new Produto(this.nome, this.valor, this.qtdDisponivel, this.descricao, caracteristicas,categoria, usuario);
	}

	public Long usuarioId(HttpServletRequest request, TokenService service) {
		String token = request.getHeader("Authorization");
		token = token.substring(7, token.length());
		Long usuarioId = service.getIdUsuario(token);
		return usuarioId;
	}

	public boolean temCaracteristicasIguais() {
		HashSet<String> nomesIguais = new HashSet<>();
		for (NovaCaracteristicaRequest caracteristica : caracteristicas) {
			if (!nomesIguais.add(caracteristica.getNome())) {
				return true;
			}
		}
		return false;
	}

	public String getNome() {
		return nome;
	}

	public List<NovaCaracteristicaRequest> getCaracteristicas() {
		return caracteristicas;
	}

	public String getDescricao() {
		return descricao;
	}

}
