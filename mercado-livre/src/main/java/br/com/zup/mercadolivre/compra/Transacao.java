package br.com.zup.mercadolivre.compra;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private StatusTransacao status;
	private @NotBlank String idTransacaoGateway;
	
	@ManyToOne
	@NotNull
	private Compra compra;
	private LocalDateTime instante = LocalDateTime.now();
	
	
	@Deprecated
	public Transacao() {
		
	}
	
	public Transacao(@NotNull StatusTransacao status, @NotBlank String idTransacao, Compra compra) {
		this.status = status;
		this.idTransacaoGateway = idTransacao;
		this.compra = compra;
	}

	public boolean concluidaComSucesso() {
		return this.status.equals(StatusTransacao.sucesso);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idTransacaoGateway);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transacao other = (Transacao) obj;
		return Objects.equals(idTransacaoGateway, other.idTransacaoGateway);
	}

	
	
}
