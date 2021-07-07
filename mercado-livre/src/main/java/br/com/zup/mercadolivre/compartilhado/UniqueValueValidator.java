package br.com.zup.mercadolivre.compartilhado;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

	private Class<?> klass;
	private String domainAttribute;

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void initialize(UniqueValue params) {
		klass = params.domainClass();
		domainAttribute = params.fieldName();
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		
		Query query = manager.createQuery(
				"select 1 from " +klass.getName() + " where " + domainAttribute + "=:obj");
		query.setParameter("obj", obj);
		
		List<?> list = query.getResultList();
		
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(
				"Já existe um registro em "+klass.getSimpleName()+" com essa informação: "+obj)
				.addConstraintViolation();
		
		return list.isEmpty();
	}

}
