package br.com.zup.mercadolivre.compartilhado;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class ExistIdValidator implements ConstraintValidator<ExistId, Long> {

	private Class<?> klass;
	private String domainAttribute;

	@PersistenceContext
	EntityManager manager;

	public void initialize(ExistId params) {
		klass = params.domainClass();
		domainAttribute = params.fieldName();
	}

	@Override
	public boolean isValid(Long obj, ConstraintValidatorContext context) {

		Query query = manager.createQuery(
				"select 1 from " +klass.getName()+" where "+domainAttribute+"=:obj");
		query.setParameter("obj", obj);

		List<?> list = query.getResultList();

		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate("Não foi encontrado "+klass.getSimpleName()+" com esse id: "+obj)
		.addConstraintViolation();
		
		return !list.isEmpty();
	}

}
