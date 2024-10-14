package edu.es.eoi.marketplace.dto;

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
public class ArticuloDto {
	private Integer id;
	private String nombre;
	private double precio;
	private int stock;
}
