package br.com.zup.mercadolivre.pergunta;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zup.mercadolivre.login.TokenService;
import br.com.zup.mercadolivre.produto.Produto;
import br.com.zup.mercadolivre.usuario.Usuario;

public class PerguntaRequest {
	
	@NotBlank
	private String titulo;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public PerguntaRequest(@NotBlank String titulo) {
		this.titulo = titulo;
	}

	public Pergunta toModel(EntityManager manager, Long id, Long usuarioId) {
		Produto produto = manager.find(Produto.class, id);
		Usuario usuario = manager.find(Usuario.class, usuarioId);
		return new Pergunta(titulo, usuario, produto);
	}

	public Long usuarioId(HttpServletRequest request, TokenService service) {
		String token = request.getHeader("Authorization");
		token = token.substring(7, token.length());
		Long usuarioId = service.getIdUsuario(token);
		return usuarioId;
	}

}
