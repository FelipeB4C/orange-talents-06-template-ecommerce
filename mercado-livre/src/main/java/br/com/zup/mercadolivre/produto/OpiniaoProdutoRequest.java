package br.com.zup.mercadolivre.produto;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zup.mercadolivre.login.TokenService;
import br.com.zup.mercadolivre.usuario.Usuario;

public class OpiniaoProdutoRequest {

	@Min(1)
	@Max(5)
	private Integer nota;

	@NotBlank
	private String titulo;

	@NotBlank
	@Size(max = 500)
	private String descricao;

	@NotNull
	private Long usuarioId;

	@NotNull
	private Long produtoId;

	public OpiniaoProdutoRequest(@Min(1) @Max(5) Integer nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public OpiniaoProduto toModel(EntityManager manager, Long idUsuario, Long idProduto) {
		Produto produto = manager.find(Produto.class, idProduto);
		Usuario usuario = manager.find(Usuario.class, idUsuario);
		return new OpiniaoProduto(this.nota, this.titulo, this.descricao, usuario, produto);
	}

	public Long usuarioId(HttpServletRequest request, TokenService service) {
		String token = request.getHeader("Authorization");
		token = token.substring(7, token.length());
		Long usuarioId = service.getIdUsuario(token);
		return usuarioId;
	}

}
