package edu.es.eoi.marketplace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.es.eoi.marketplace.dto.ArticuloDto;
import edu.es.eoi.marketplace.service.ArtitculoService;


@RestController
@RequestMapping("/marketplace/articulos")
public class ArticulosController {
	@Autowired
	private ArtitculoService artitculoService;

	@GetMapping("/{name}/nombre")
	public ResponseEntity<List<ArticuloDto>> getAllByPartialName(@PathVariable String name) {
		List<ArticuloDto> articulos = artitculoService.findAllByPartialName(name);

		if (articulos == null) {
			return new ResponseEntity<List<ArticuloDto>>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<ArticuloDto>>(articulos, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ArticuloDto> getById(@PathVariable int id) {
		ArticuloDto articulo = artitculoService.findById(id);

		if (articulo == null) {
			return new ResponseEntity<ArticuloDto>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ArticuloDto>(articulo, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> createArticulo(@RequestBody ArticuloDto articulo) {
		if (artitculoService.createArticulo(articulo)) {
			return new ResponseEntity<String>(HttpStatus.CREATED);
		}

		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateArticulo(@PathVariable int id, @RequestBody ArticuloDto articulo) {
		if (artitculoService.updateArticulo(id, articulo)) {
			return new ResponseEntity<String>(HttpStatus.CREATED);
		}

		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}

}
