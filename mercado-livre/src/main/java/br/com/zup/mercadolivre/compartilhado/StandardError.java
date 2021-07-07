package br.com.zup.mercadolivre.compartilhado;

public class StandardError {

	private String campo;
	private String msgErro;

	public StandardError(String campo, String msgErro) {
		this.campo = campo;
		this.msgErro = msgErro;
	}
	
	public String getCampo() {
		return campo;
	}

	public String getMsgErro() {
		return msgErro;
	}

}
