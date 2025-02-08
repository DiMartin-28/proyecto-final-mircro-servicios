
package com.dimartin.serviceventa.repository;

import com.dimartin.serviceventa.dto.CarritoDTO;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "carrito", url = "http://localhost:8082/carrito")
public interface ICarritoFeignRepository {
    
    // METODO DE PRUEBA(REVISAR)
    @GetMapping("/getall")
    public List<CarritoDTO> getAllCarritos();
    
    // METODO PARA TRAER UN CARRITO DTO POR SU ID
    // A ESTE EDPONT HAY QUE MODIFICARLE EL NOMBRE
    @GetMapping("/getnamebyid/{codigo}")
    public CarritoDTO getCarrDtoById(@PathVariable Long codigo);
}
