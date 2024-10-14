package edu.es.eoi.marketplace.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "compra")
public class Compra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_articulo", referencedColumnName = "id")
	private Articulo articulo;

	@ManyToOne
	@JoinColumn(name = "id_pedido", referencedColumnName = "id")
	private Pedido pedido;
	@Column
	private int cantidad;
//	@ManyToOne
//	@MapsId("idArticulo")
//	@JoinColumn(name = "id_articulo")
//	private Articulo articulo;
//
//	@ManyToOne
//	@MapsId("idPedido")
//	@JoinColumn(name = "id_pedido")
//	private Pedido pedido;

}
