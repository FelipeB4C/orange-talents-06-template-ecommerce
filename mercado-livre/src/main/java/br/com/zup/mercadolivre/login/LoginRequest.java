package br.com.zup.mercadolivre.login;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class LoginRequest {

	@Email
	@NotBlank
	private String login;

	@NotBlank
	private String senha;

	public void setLogin(String login) {
		this.login = login;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Authentication geraOAuthentication() {
		return new UsernamePasswordAuthenticationToken(this.login, this.senha);
	}
	
}
