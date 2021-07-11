package br.com.zup.mercadolivre.detalhesproduto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.SortedSet;
import java.util.stream.IntStream;

import br.com.zup.mercadolivre.produto.Opinioes;
import br.com.zup.mercadolivre.produto.Produto;

public class DetalhesResponse {

	private String nome;
	private String descricao;
	private BigDecimal valor;
	private Integer qtdDisponível;
	private Set<DetalheProdutoCaracteristica> caracteristicas;
	private Set<String> linksImagens;
	private SortedSet<String> perguntas;
	private Set<Map<String,String>> opinioes;
	private double mediaNotas;

	public DetalhesResponse(Produto produto) {
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.valor = produto.getValor();
		this.qtdDisponível = produto.getQtdDisponivel();
		this.caracteristicas = produto.mapCaracteristicas(DetalheProdutoCaracteristica::new);
		this.linksImagens = produto.mapImagens(imagem -> imagem.getLink());
		this.perguntas = produto.mapPerguntas(pergunta -> pergunta.getTitulo());
		
		Opinioes opinioes = produto.getOpinioes();
		
		this.opinioes = opinioes.mapOpinioes(opiniao -> {
			return Map.of("titulo",opiniao.getTitulo(),"descricao",opiniao.getDescricao());
		});
		
		Set<Integer> notas = opinioes.mapOpinioes(opiniao -> opiniao.getNota());
		
		IntStream mapToInt = notas.stream().mapToInt(nota -> nota);
		OptionalDouble average = mapToInt.average();
		if (average.isPresent()) {
			this.mediaNotas = average.getAsDouble();
		}

	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Integer getQtdDisponível() {
		return qtdDisponível;
	}

	public Set<DetalheProdutoCaracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public Set<String> getLinksImagens() {
		return linksImagens;
	}

	public SortedSet<String> getPerguntas() {
		return perguntas;
	}

	public Set<Map<String, String>> getOpinioes() {
		return opinioes;
	}

	public double getMediaNotas() {
		return mediaNotas;
	}
}
