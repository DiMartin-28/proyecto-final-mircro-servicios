
package com.dimartin.servicecarrito.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {
    
    private Long codigo;
    private String nombre;
    private String marca;
    private double precio;
    private int cantidad;
    
}
