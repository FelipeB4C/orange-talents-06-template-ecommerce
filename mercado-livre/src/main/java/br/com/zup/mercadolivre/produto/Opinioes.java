package br.com.zup.mercadolivre.produto;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Opinioes extends OpiniaoProduto {

	private Set<OpiniaoProduto> opinioes;
	
	public Opinioes(Set<OpiniaoProduto> opinioes) {
		this.opinioes = opinioes;
	}
	
	public <T> Set<T> mapOpinioes(Function<OpiniaoProduto, T> funcaoMapeadora) {
		return this.opinioes.stream().map(funcaoMapeadora)
				.collect(Collectors.toSet());
	}

}
