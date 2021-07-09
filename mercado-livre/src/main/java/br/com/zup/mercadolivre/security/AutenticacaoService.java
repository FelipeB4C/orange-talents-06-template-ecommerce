package br.com.zup.mercadolivre.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.zup.mercadolivre.usuario.Usuario;
import br.com.zup.mercadolivre.usuario.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	UsuarioRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Usuario> usuario = repo.findByLogin(username);
		if(usuario.isPresent()) {
			return usuario.get();
		} throw new UsernameNotFoundException("Dados inválidos");
	}

}
