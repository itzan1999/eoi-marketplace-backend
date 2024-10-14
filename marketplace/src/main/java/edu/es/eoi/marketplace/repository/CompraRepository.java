package edu.es.eoi.marketplace.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.es.eoi.marketplace.entity.Compra;


public interface CompraRepository extends JpaRepository<Compra, Integer> {
	@Modifying
	@Transactional
	@Query("DELETE FROM Compra WHERE id_pedido = :idPedido")
	public void deleteArticulosPedidoByIdPedido(@Param("idPedido") int id);

	@Query(value = "SELECT id_articulo FROM Articulo INNER JOIN Compra ON Articulo.id = id_articulo GROUP BY id_articulo ORDER BY sum(cantidad) DESC LIMIT 10", nativeQuery = true)
	public List<Integer> articulosMasVendidos();

	@Query(value = "SELECT id_pedido FROM Compra INNER JOIN Articulo ON Articulo.id = id_articulo GROUP BY id_pedido ORDER BY round(sum(precio * cantidad), 2) DESC LIMIT 3", nativeQuery = true)
	public List<Integer> topPedidosMasCaros();

	@Query(value = "SELECT id_user FROM Compra INNER JOIN articulo ON Articulo.id = id_articulo INNER JOIN Pedido ON Pedido.id = id_pedido GROUP BY id_user ORDER BY sum(precio * cantidad) DESC", nativeQuery = true)
	public List<Integer> mejoresClientes();
}
