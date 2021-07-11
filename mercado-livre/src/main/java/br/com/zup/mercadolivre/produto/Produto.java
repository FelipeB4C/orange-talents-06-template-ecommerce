package br.com.zup.mercadolivre.produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import br.com.zup.mercadolivre.categoria.Categoria;
import br.com.zup.mercadolivre.pergunta.Pergunta;
import br.com.zup.mercadolivre.usuario.Usuario;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;

	@Positive
	@NotNull
	private BigDecimal valor;

	@NotNull
	@PositiveOrZero
	private Integer qtdDisponivel;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

	@NotBlank
	@Size(max = 1000)
	private String descricao;

	@NotNull
	@ManyToOne
	private Categoria categoria;

	@NotNull
	@ManyToOne
	private Usuario dono;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<ImagemProduto> imagens = new HashSet<>();

	@OneToMany(mappedBy = "produto")
	@OrderBy("titulo asc")
	private Set<Pergunta> perguntas = new TreeSet<>();

	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<OpiniaoProduto> opinioes = new HashSet<>();

	private LocalDateTime instante = LocalDateTime.now();

	@Deprecated
	public Produto() {

	}

	public Produto(@NotBlank String nome, @Positive @NotNull BigDecimal valor,
			@NotNull @PositiveOrZero Integer qtdDisponivel, @NotBlank @Size(max = 1000) String descricao,
			Collection<NovaCaracteristicaRequest> caracteristicas, @NotNull Categoria categoria,
			@NotNull Usuario dono) {
		this.nome = nome;
		this.valor = valor;
		this.qtdDisponivel = qtdDisponivel;
		this.descricao = descricao;
		this.categoria = categoria;
		this.dono = dono;
		this.caracteristicas.addAll(caracteristicas.stream().map(caracteristica -> caracteristica.toModel(this))
				.collect(Collectors.toSet()));
	}

	public void associaImagens(Set<String> links) {
		Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this, link))
				.collect(Collectors.toSet());
		this.imagens.addAll(imagens);
	}

	public boolean pertenceAoUsuario(Usuario possivelDono) {
		return this.dono.equals(possivelDono);
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Integer getQtdDisponivel() {
		return qtdDisponivel;
	}

	public Set<CaracteristicaProduto> getCaracteristicas() {
		return caracteristicas;
	}

	public <T> Set<T> mapCaracteristicas(Function<CaracteristicaProduto, T> funcaoMapeadora) {
		return this.caracteristicas.stream().map(funcaoMapeadora).collect(Collectors.toSet());
	}

	public String getDescricao() {
		return descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Set<ImagemProduto> getImagens() {
		return imagens;
	}

	public Usuario getDono() {
		return dono;
	}

	public Opinioes getOpinioes() {
		return new Opinioes(this.opinioes);
	}

	public <T> Set<T> mapImagens(Function<ImagemProduto, T> funcaoMapeadora) {
		return this.imagens.stream().map(funcaoMapeadora).collect(Collectors.toSet());
	}

	public <T extends Comparable<T>> SortedSet<T> mapPerguntas(Function<Pergunta, T> funcaoMapeadora) {
		return this.perguntas.stream().map(funcaoMapeadora).collect(Collectors.toCollection(TreeSet::new));
	}

}
