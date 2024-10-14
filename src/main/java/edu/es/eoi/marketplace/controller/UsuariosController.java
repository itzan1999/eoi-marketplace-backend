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

import edu.es.eoi.marketplace.dto.UsuarioDto;
import edu.es.eoi.marketplace.service.UsuarioService;


@RestController
@RequestMapping("/marketplace/usuarios")
public class UsuariosController {
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<UsuarioDto>> getAllUsers() {
		return new ResponseEntity<List<UsuarioDto>>(usuarioService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDto> getUserById(@PathVariable int id) {
		UsuarioDto usuario = usuarioService.findById(id);

		if (usuario == null) {
			return new ResponseEntity<UsuarioDto>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<UsuarioDto>(usuario, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> createUser(@RequestBody UsuarioDto user) {
		if (usuarioService.createUsuario(user)) {
			return new ResponseEntity<String>(HttpStatus.CREATED);
		}

		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/login")
	public ResponseEntity<UsuarioDto> login(@RequestBody UsuarioDto login) {
		UsuarioDto user = usuarioService.login(login);

		if (user == null) {
			return new ResponseEntity<UsuarioDto>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<UsuarioDto>(user, HttpStatus.ACCEPTED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateUsuario(@PathVariable int id, @RequestBody UsuarioDto user) {
		if (usuarioService.updateUsuario(id, user)) {
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		}

		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}
}
