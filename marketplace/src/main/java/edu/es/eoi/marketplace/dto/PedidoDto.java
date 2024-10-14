package edu.es.eoi.marketplace.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoDto {
	private Integer id;
	private String nombre;
	private Integer idUsuario;
	private Date fecha;
	private List<CompraDto> articulos;
}
