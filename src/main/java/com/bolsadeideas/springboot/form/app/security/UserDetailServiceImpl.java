package com.bolsadeideas.springboot.form.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.form.app.dao.IUsuarioDao;
import com.bolsadeideas.springboot.form.app.entity.Usuario;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private IUsuarioDao usuarioDao;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByEmail(email)
		.orElseThrow(() -> new UsernameNotFoundException("El usuario con email " + email + " no existe"));
		
		return new UserDetailsImpl(usuario);
	}
	
	public String getNombreRol() throws UsernameNotFoundException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = (String) authentication.getPrincipal();
		Usuario usuario = usuarioDao.findByEmail(email)
		.orElseThrow(() -> new UsernameNotFoundException("El usuario con email " + email + " no existe"));
		
		return new UserDetailsImpl(usuario).getNombreRol();
	}
	
	public UserDetails loadUser() throws UsernameNotFoundException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = (String) authentication.getPrincipal();
		Usuario usuario = usuarioDao.findByEmail(email)
		.orElseThrow(() -> new UsernameNotFoundException("El usuario con email " + email + " no existe"));
		
		return new UserDetailsImpl(usuario);
	}

}
