
package com.integrador.crudbazar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VentaDto {
    private Long codigo_venta;
    private Double total;
    private int cantidad_productos;
    private String nombre_cliente;
    public String apellido_cliente;
}
