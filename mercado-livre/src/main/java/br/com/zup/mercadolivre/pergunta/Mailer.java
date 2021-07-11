package br.com.zup.mercadolivre.pergunta;

public interface Mailer {

	/**
	 * 
	 * @param body - corpo do email
	 * @param subject - assunto do email
	 * @param nameFrom - nome do remetente
	 * @param from - email de origem
	 * @param to - email de destino
	 * 
	 * */
	void send(String body, String subject, String nameFrom, String from, String to);
	
}
