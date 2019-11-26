package com.tcc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tcc.domain.Cliente;
import com.tcc.repositories.ClienteRepository;
import com.tcc.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Cliente cliente = clienteRepository.findByEmail(username);
		if (cliente == null) {
			throw new UsernameNotFoundException(username);
		}
		return new UserSS(cliente.getId(), cliente.getEmail(), cliente.getSenha(), cliente.getPerfis());
	}

}