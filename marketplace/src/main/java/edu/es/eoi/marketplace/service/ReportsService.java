package edu.es.eoi.marketplace.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.es.eoi.marketplace.dto.ArticuloDto;
import edu.es.eoi.marketplace.dto.CompraDto;
import edu.es.eoi.marketplace.dto.PedidoDto;
import edu.es.eoi.marketplace.dto.UsuarioDto;
import edu.es.eoi.marketplace.entity.Articulo;
import edu.es.eoi.marketplace.entity.Compra;
import edu.es.eoi.marketplace.entity.Pedido;
import edu.es.eoi.marketplace.entity.Usuario;
import edu.es.eoi.marketplace.repository.ArticuloRepository;
import edu.es.eoi.marketplace.repository.CompraRepository;
import edu.es.eoi.marketplace.repository.PedidoRepository;
import edu.es.eoi.marketplace.repository.UsuarioRepository;


@Service
public class ReportsService {
	@Autowired
	private CompraRepository compraRepository;
	@Autowired
	private ArticuloRepository articuloRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	private UsuarioDto entityToDtoUsuario(Usuario entity) {
		UsuarioDto dto = new UsuarioDto();
		dto.setId(entity.getId());
		dto.setNombre(entity.getNombre());
		dto.setPassword(entity.getPassword());
		return dto;
	}

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

	private PedidoDto entityToDtoPedido(Pedido entity) {
		PedidoDto dto = new PedidoDto();
		dto.setId(entity.getId());
		dto.setNombre(entity.getNombre());
		dto.setIdUsuario(entity.getUsuario().getId());
		dto.setFecha(entity.getFecha());
		dto.setArticulos(getArticulosPedidoDto(entity.getCompra()));

		return dto;
	}

	private ArticuloDto entityToDtoArticulo(Articulo entity) {
		ArticuloDto dto = new ArticuloDto();
		dto.setId(entity.getId());
		dto.setNombre(entity.getNombre());
		dto.setPrecio(entity.getPrecio());
		dto.setStock(entity.getStock());
		return dto;
	}

	public List<ArticuloDto> getTopArticulosMasVendidos() {
		List<ArticuloDto> top = new ArrayList<ArticuloDto>();

		compraRepository.articulosMasVendidos().forEach(id -> {
			top.add(entityToDtoArticulo(articuloRepository.findById(id).get()));
		});

		return top;
	}

	public List<PedidoDto> getTopPedidos() {
		List<PedidoDto> top = new ArrayList<PedidoDto>();

		compraRepository.topPedidosMasCaros().forEach(id -> {
			top.add(entityToDtoPedido(pedidoRepository.findById(id).get()));
		});

		return top;
	}

	public List<UsuarioDto> getTopUsuarios() {
		List<UsuarioDto> top = new ArrayList<UsuarioDto>();

		compraRepository.mejoresClientes().forEach(id -> {
			top.add(entityToDtoUsuario(usuarioRepository.findById(id).get()));
		});

		return top;
	}
}
