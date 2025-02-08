
package com.dimartin.servicecarrito.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    
    private Long codigo;
    private String nombre;
    private String marca;
    private double precio;
    
}
