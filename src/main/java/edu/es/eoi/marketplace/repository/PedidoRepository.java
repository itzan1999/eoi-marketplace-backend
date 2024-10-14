package edu.es.eoi.marketplace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.es.eoi.marketplace.entity.Pedido;


public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	public List<Pedido> findByNombreContaining(String partialName);
}
