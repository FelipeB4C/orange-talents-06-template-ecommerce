package br.com.zup.mercadolivre.compra;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoNovaCompra {

	@Autowired
	private Set<EventoCompraSucesso> eventosCompraSucesso;
	
	public void processa(Compra compra) {
		if(compra.processadaComSucesso()) {
			eventosCompraSucesso.forEach(evento -> evento.processa(compra));
		}
	}

}
