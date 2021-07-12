package br.com.zup.mercadolivre.outrossistemas;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OutrosSistemasResource {

	@PostMapping(value = "/notas-fiscais")
	public void criaNota(@RequestBody @Valid CompraNFRequest request) throws InterruptedException {
		System.out.println("criando nota: "+request);
		Thread.sleep(150);
	}
	
	@PostMapping(value = "/ranking")
	public void ranking(@RequestBody @Valid RankingCompraRequest request) throws InterruptedException {
		System.out.println("criando ranking: "+request);
		Thread.sleep(150);
	}
	
}
