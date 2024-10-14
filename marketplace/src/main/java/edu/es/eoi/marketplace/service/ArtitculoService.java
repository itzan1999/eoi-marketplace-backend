package edu.es.eoi.marketplace.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.es.eoi.marketplace.dto.ArticuloDto;
import edu.es.eoi.marketplace.entity.Articulo;
import edu.es.eoi.marketplace.repository.ArticuloRepository;


@Service
public class ArtitculoService {
	@Autowired
	private ArticuloRepository articuloRepository;

	private ArticuloDto entityToDto(Articulo entity) {
		ArticuloDto dto = new ArticuloDto();
		dto.setId(entity.getId());
		dto.setNombre(entity.getNombre());
		dto.setPrecio(entity.getPrecio());
		dto.setStock(entity.getStock());
		return dto;
	}

	private Articulo dtoToEntity(ArticuloDto dto) {
		Articulo entity = new Articulo();
		entity.setId(dto.getId());
		entity.setNombre(dto.getNombre());
		entity.setPrecio(dto.getPrecio());
		entity.setStock(dto.getStock());
		return entity;
	}

	public List<ArticuloDto> findAllByPartialName(String nombre) {
		if (nombre.isEmpty() || nombre == null) {
			return null;
		}

		List<ArticuloDto> dtos = new ArrayList<ArticuloDto>();

		articuloRepository.findByNombreContaining(nombre).forEach(a -> dtos.add(entityToDto(a)));

		if (dtos.size() == 0) return null;

		return dtos;
	}

	public ArticuloDto findById(int id) {
		Optional<Articulo> entity = articuloRepository.findById(id);
		if (entity.isPresent()) {
			return entityToDto(entity.get());
		}

		return null;
	}

	public boolean createArticulo(ArticuloDto dto) {
		if (dto.getNombre() == null || dto.getNombre().isEmpty() || dto.getPrecio() <= 0 || dto.getStock() < 0) {
			return false;
		}

		dto.setId(null);

		articuloRepository.save(dtoToEntity(dto));
		return true;
	}

	public boolean updateArticulo(int id, ArticuloDto dto) {
		if (dto.getId() == null || dto.getId() != id || dto.getNombre() == null || dto.getNombre().isEmpty() || dto.getPrecio() <= 0
				|| dto.getStock() < 0) {
			return false;
		}

		if (!articuloRepository.findById(id).isPresent()) return false;

		articuloRepository.save(dtoToEntity(dto));
		return true;
	}
}
