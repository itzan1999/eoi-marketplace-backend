package edu.es.eoi.marketplace.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.es.eoi.marketplace.dto.UsuarioDto;
import edu.es.eoi.marketplace.entity.Usuario;
import edu.es.eoi.marketplace.repository.UsuarioRepository;


@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	private UsuarioDto entityToDto(Usuario entity) {
		UsuarioDto dto = new UsuarioDto();
		dto.setId(entity.getId());
		dto.setNombre(entity.getNombre());
		dto.setPassword(entity.getPassword());
		return dto;
	}

	private Usuario dtoToEntity(UsuarioDto dto) {
		Usuario entity = new Usuario();
		entity.setId(dto.getId());
		entity.setNombre(dto.getNombre());
		entity.setPassword(dto.getPassword());
		return entity;
	}

	public List<UsuarioDto> findAll() {
		List<Usuario> entities = usuarioRepository.findAll();
		List<UsuarioDto> dtos = new ArrayList<UsuarioDto>();

		for (Usuario entity : entities) {
			dtos.add(entityToDto(entity));
		}

		return dtos;
	}

	public UsuarioDto findById(int id) {
		Optional<Usuario> entity = usuarioRepository.findById(id);
		if (entity.isPresent()) {
			return entityToDto(entity.get());
		}

		return null;
	}

	public boolean createUsuario(UsuarioDto dto) {
		if (dto.getNombre() == null || dto.getNombre().isEmpty() || dto.getPassword() == null || dto.getPassword().isEmpty()) {
			return false;
		}

		dto.setId(null);

		usuarioRepository.save(dtoToEntity(dto));
		return true;
	}

	public boolean updateUsuario(int id, UsuarioDto dto) {
		if (dto.getId() != id || dto.getNombre() == null || dto.getNombre().isEmpty() || dto.getPassword() == null || dto.getPassword().isEmpty()) {
			return false;
		}

		if (!usuarioRepository.findById(id).isPresent()) return false;

		usuarioRepository.save(dtoToEntity(dto));
		return true;
	}

	public UsuarioDto login(UsuarioDto login) {
		if (login.getNombre().isEmpty() || login.getNombre() == null || login.getPassword().isEmpty() || login.getPassword() == null) {
			return null;
		}
		List<Usuario> entity = usuarioRepository.findFirstByNombre(login.getNombre());

		if (entity.size() == 0) {
			return null;
		}

		if (!entity.get(0).getPassword().equals(login.getPassword())) {
			return null;
		}

		return entityToDto(entity.get(0));
	}
}
