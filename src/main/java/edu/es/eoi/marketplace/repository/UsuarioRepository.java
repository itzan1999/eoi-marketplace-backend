package edu.es.eoi.marketplace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.es.eoi.marketplace.entity.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	public List<Usuario> findFirstByNombre(String nombre);
}
