package edu.es.eoi.marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.es.eoi.marketplace.repository.CompraRepository;


@Service
public class CompraService {
	@Autowired
	private CompraRepository compraRepository;

//	private CompraDto entityToDto(Compra entity) {
//		CompraDto dto = new CompraDto();
//		dto.setId(entity.getId().getIdArticulo());
//		dto.setCantidad(entity.getCantidad());
//
//		return dto;
//	}

//	private Compra dtoToEntity(CompraDto dto) {
//		Compra entity = new Compra();
//		entity.setId(dto.getId());
//	}
}
