package br.com.zup.mercadolivre.login;

public class TokenResponse {

	private String token, tipo;

	public TokenResponse(String token, String tipo) {
		this.token = token;
		this.tipo = tipo;
	}

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}

}
