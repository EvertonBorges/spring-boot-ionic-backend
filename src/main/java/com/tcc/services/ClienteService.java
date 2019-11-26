package com.tcc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tcc.domain.Cliente;
import com.tcc.dto.ClienteDTO;
import com.tcc.dto.ClienteNewDTO;
import com.tcc.enums.Perfil;
import com.tcc.enums.TipoCliente;
import com.tcc.repositories.ClienteRepository;
import com.tcc.security.UserSS;
import com.tcc.services.exceptions.AuthorizationException;
import com.tcc.services.exceptions.DataIntegrityException;
import com.tcc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public Cliente find(Integer id)  {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasHole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado!");
		}
		
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " +
				id + ", Tipo: " + Cliente.class.getName()));
	}

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionadas");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Cliente findByEmail(String email) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasHole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado.");
		}
		
		Cliente obj = repo.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + user.getId()
					+ ", Tipo: " + Cliente.class.getName());
		}
		return obj;
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cliente = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()), pe.encode(objDto.getSenha()));
		cliente.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2() != null) {
			cliente.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3() != null) {
			cliente.getTelefones().add(objDto.getTelefone3());
		}
		
		return cliente;
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
}