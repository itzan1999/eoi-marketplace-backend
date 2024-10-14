package edu.es.eoi.marketplace.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.es.eoi.marketplace.dto.CompraDto;
import edu.es.eoi.marketplace.dto.PedidoDto;
import edu.es.eoi.marketplace.entity.Articulo;
import edu.es.eoi.marketplace.entity.Compra;
import edu.es.eoi.marketplace.entity.Pedido;
import edu.es.eoi.marketplace.repository.ArticuloRepository;
import edu.es.eoi.marketplace.repository.CompraRepository;
import edu.es.eoi.marketplace.repository.PedidoRepository;
import edu.es.eoi.marketplace.repository.UsuarioRepository;


@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private CompraRepository compraRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private ArticuloRepository articuloRepository;

	private List<CompraDto> getArticulosPedidoDto(List<Compra> entity) {
		List<CompraDto> articulos = new ArrayList<CompraDto>();
		entity.forEach(a -> {
			CompraDto articulo = new CompraDto();
			articulo.setId(a.getArticulo().getId());
			articulo.setCantidad(a.getCantidad());
			articulos.add(articulo);
		});

		return articulos;
	}

	private List<Compra> getArticulosPedidoEntity(List<CompraDto> dto, int idPedido) {
		List<Compra> articulos = new ArrayList<Compra>();
//		compraRepository.del
		dto.forEach(a -> {
			Compra articulo = new Compra();
			articulo.setArticulo(articuloRepository.findById(a.getId()).get());
			articulo.setPedido(pedidoRepository.findById(idPedido).get());
			articulo.setCantidad(a.getCantidad());

			articulos.add(articulo);
		});

		return articulos;
	}

	private PedidoDto entityToDto(Pedido entity) {
		PedidoDto dto = new PedidoDto();
		dto.setId(entity.getId());
		dto.setNombre(entity.getNombre());
		dto.setIdUsuario(entity.getUsuario().getId());
		dto.setFecha(entity.getFecha());
		dto.setArticulos(getArticulosPedidoDto(entity.getCompra()));

		return dto;
	}

	private Pedido dtoToEntity(PedidoDto dto) {
		Pedido entity = new Pedido();
		entity.setId(dto.getId());
		entity.setNombre(dto.getNombre());
		entity.setFecha(dto.getFecha());
		entity.setUsuario(usuarioRepository.findById(dto.getIdUsuario()).get());
		if (dto.getId() != null) {
			entity.setCompra(getArticulosPedidoEntity(dto.getArticulos(), dto.getId()));
		}

		return entity;
	}

	public PedidoDto findPedidoById(int id) {
		Optional<Pedido> entity = pedidoRepository.findById(id);
		if (entity.isPresent()) {
			return entityToDto(entity.get());
		}

		return null;
	}

	public List<PedidoDto> findAllByPartialName(String nombre) {
		if (nombre.isEmpty() || nombre == null) {
			return null;
		}

		List<PedidoDto> dtos = new ArrayList<PedidoDto>();

		pedidoRepository.findByNombreContaining(nombre).forEach(p -> dtos.add(entityToDto(p)));

		if (dtos.size() == 0) return null;

		return dtos;
	}

	public boolean createPedido(PedidoDto dto) {
		boolean allArticulosValidos = true;

		if (dto.getArticulos() == null || dto.getArticulos().size() <= 0 || dto.getIdUsuario() == null || dto.getFecha() == null
				|| dto.getNombre() == null || dto.getNombre().isEmpty()) {
			return false;
		}

		if (!usuarioRepository.findById(dto.getIdUsuario()).isPresent()) {
			return false;
		}

		dto.setId(null);
		Pedido entity = pedidoRepository.save(dtoToEntity(dto));
		dto.setId(entity.getId());

		for (CompraDto a : dto.getArticulos()) {
			Optional<Articulo> result = articuloRepository.findById(a.getId());
			if (result.isPresent()) {
				Articulo articulo = result.get();
				articulo.setStock(articulo.getStock() - a.getCantidad());
				articuloRepository.save(articulo);
			} else {
				allArticulosValidos = false;
			}
		}

		if (!allArticulosValidos) {
			return false;
		}

		pedidoRepository.save(dtoToEntity(dto));
		return true;
	}

	public boolean updatePedido(int id, PedidoDto dto) {
		if (dto.getId() == null || dto.getId() != id || dto.getArticulos() == null || dto.getArticulos().size() <= 0 || dto.getIdUsuario() == null
				|| dto.getFecha() == null || dto.getNombre() == null || dto.getNombre().isEmpty()) {
			return false;
		}

		if (!pedidoRepository.findById(id).isPresent()) return false;

		compraRepository.deleteArticulosPedidoByIdPedido(id);

		System.out.println(dto.getArticulos().size());
		System.out.println(pedidoRepository.findById(id).get().getCompra().size());
		pedidoRepository.save(dtoToEntity(dto));
		return true;
	}

	public boolean deletePedidoById(int id) {
		if (!pedidoRepository.findById(id).isPresent()) return false;

		pedidoRepository.deleteById(id);
		return true;
	}
}
