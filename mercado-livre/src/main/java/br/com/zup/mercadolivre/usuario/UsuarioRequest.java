package br.com.zup.mercadolivre.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.zup.mercadolivre.compartilhado.UniqueValue;

public class UsuarioRequest {

	@UniqueValue(domainClass = Usuario.class, fieldName = "login")
	@Email
	@NotBlank
	private String login;

	@NotBlank
	@Size(min = 6)
	private String senha;

	public UsuarioRequest(@Email @NotBlank String login, @NotBlank @Size(min = 6) String senha) {
		this.login = login;
		this.senha = senha;
	}

	public Usuario toModel() {
		return new Usuario(login, senha);
	}

}
