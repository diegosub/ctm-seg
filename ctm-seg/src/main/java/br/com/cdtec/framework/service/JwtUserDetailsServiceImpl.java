package br.com.cdtec.framework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.cdtec.framework.jwt.JwtUserFactory;
import br.com.cdtec.framework.model.entity.Usuario;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String dsLogin) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioService.pesquisarPorLogin(dsLogin);
		
		if(usuario == null) {
			throw new UsernameNotFoundException(String.format("Nenhum usuário encontrado com login: ", dsLogin));
		} else {
			return JwtUserFactory.create(usuario);
		}
		
	}

	
	
}
