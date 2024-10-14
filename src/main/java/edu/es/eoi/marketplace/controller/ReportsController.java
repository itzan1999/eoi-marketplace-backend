package edu.es.eoi.marketplace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.es.eoi.marketplace.dto.ArticuloDto;
import edu.es.eoi.marketplace.dto.PedidoDto;
import edu.es.eoi.marketplace.dto.UsuarioDto;
import edu.es.eoi.marketplace.service.ReportsService;


@RestController
@RequestMapping("/reports")
public class ReportsController {
	@Autowired
	private ReportsService reportsService;

	@GetMapping("/articulosMasVendidos")
	public ResponseEntity<List<ArticuloDto>> getTopVentas() {
		return new ResponseEntity<List<ArticuloDto>>(reportsService.getTopArticulosMasVendidos(), HttpStatus.OK);
	}

	@GetMapping("/mejoresPedidos")
	public ResponseEntity<List<PedidoDto>> getTopPedidos() {
		return new ResponseEntity<List<PedidoDto>>(reportsService.getTopPedidos(), HttpStatus.OK);
	}

	@GetMapping("/mejoresUsuarios")
	public ResponseEntity<List<UsuarioDto>> getTopUsuarios() {
		return new ResponseEntity<List<UsuarioDto>>(reportsService.getTopUsuarios(), HttpStatus.OK);
	}
}
