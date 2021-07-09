package br.com.zup.mercadolivre.login;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.zup.mercadolivre.usuario.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${mercadolivre.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication auth) {
		Usuario usuario = (Usuario) auth.getPrincipal();
		
		return Jwts.builder()
				.setIssuer("Api do Mercado Livre")
				.setSubject(usuario.getId().toString())
				.setIssuedAt(new Date())
				.setExpiration(incluiUmaHoraBaseadoNaHoraAtual())
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	private Date incluiUmaHoraBaseadoNaHoraAtual() {
		return Date.from(
				LocalDateTime.now()
				.plusHours(1)
				.atZone(ZoneId.of("America/Sao_Paulo")).toInstant());
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser()
			.setSigningKey(this.secret)
			.parseClaimsJws(token);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	
	}

	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser()
		.setSigningKey(this.secret)
		.parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
