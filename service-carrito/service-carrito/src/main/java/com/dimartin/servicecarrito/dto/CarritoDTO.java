
package com.dimartin.servicecarrito.dto;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarritoDTO {
    
    private double precio_total;
    @ElementCollection(fetch = FetchType.EAGER)
    List<String> lista_productos;
    
}
