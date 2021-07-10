package br.com.zup.mercadolivre.produto;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import br.com.zup.mercadolivre.login.TokenService;

public class ImagensRequest {

	@Size(min = 1)
	@NotNull
	private List<MultipartFile> imagens = new ArrayList<>();
	
	public ImagensRequest(List<MultipartFile> imagens) {
		this.imagens = imagens;
	}

	public List<MultipartFile> getImagens() {
		return imagens;
	}
	
	public Long usuarioId(HttpServletRequest request, TokenService service) {
		String token = request.getHeader("Authorization");
		token = token.substring(7, token.length());
		Long usuarioId = service.getIdUsuario(token);
		return usuarioId;
	}
	
}
