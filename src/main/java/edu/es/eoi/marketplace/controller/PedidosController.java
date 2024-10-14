package edu.es.eoi.marketplace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.es.eoi.marketplace.dto.PedidoDto;
import edu.es.eoi.marketplace.service.PedidoService;


@RestController
@RequestMapping("/marketplace/pedidos")
public class PedidosController {
	@Autowired
	private PedidoService pedidoService;

	@GetMapping("/{id}")
	public ResponseEntity<PedidoDto> getById(@PathVariable int id) {
		PedidoDto pedido = pedidoService.findPedidoById(id);

		if (pedido == null) {
			return new ResponseEntity<PedidoDto>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<PedidoDto>(pedido, HttpStatus.OK);
	}

	@GetMapping("/{name}/nombre")
	public ResponseEntity<List<PedidoDto>> getAllByPartialName(@PathVariable String name) {
		List<PedidoDto> articulos = pedidoService.findAllByPartialName(name);

		if (articulos == null) {
			return new ResponseEntity<List<PedidoDto>>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<PedidoDto>>(articulos, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> createPedido(@RequestBody PedidoDto pedido) {
		if (pedidoService.createPedido(pedido)) {
			return new ResponseEntity<String>(HttpStatus.CREATED);
		}

		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updatePedido(@PathVariable int id, @RequestBody PedidoDto pedido) {
		if (pedidoService.updatePedido(id, pedido)) {
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		}

		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePedidoById(@PathVariable int id) {
		if (pedidoService.deletePedidoById(id)) {
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
}
