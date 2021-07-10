package br.com.zup.mercadolivre.login;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoResource {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<TokenResponse> autenticar(@RequestBody @Valid LoginRequest login) {
		try {
			Authentication auth = authManager.authenticate(login.geraOAuthentication());

			String token = tokenService.gerarToken(auth);

			return ResponseEntity.ok(new TokenResponse(token, "Bearer"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();

		}
	}

}
