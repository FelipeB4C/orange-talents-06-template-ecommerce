package br.com.zup.mercadolivre.produto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProibeCaracteristicaComNomeRepetidoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ProdutoRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		if(errors.hasErrors()) {
			return;
		}
		ProdutoRequest request = (ProdutoRequest) target;
	
		if(request.temCaracteristicasIguais()) {
			errors.rejectValue("caracteristicas", null, "Tem característica com nome igual");
		}
	
	}

}
