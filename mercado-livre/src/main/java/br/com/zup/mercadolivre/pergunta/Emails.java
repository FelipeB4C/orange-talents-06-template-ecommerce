package br.com.zup.mercadolivre.pergunta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Emails {
	
	@Autowired
	private Mailer mailer;

	public void novaPergunta(Pergunta pergunta) {
//		new RestTemplate().postForEntity("https://api.mandril.com", mandrilMessage, String.class);
		
		mailer.send("<html>...</html>", "Nova pergunta...", 
				pergunta.getPerguntador().getLogin(),
				"novapergunta@mercadolivre.com",
				pergunta.getDonoProduto().getLogin());
	}
	
}
