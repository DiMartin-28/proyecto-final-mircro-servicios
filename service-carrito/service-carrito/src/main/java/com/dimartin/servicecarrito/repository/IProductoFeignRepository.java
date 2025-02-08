 
package com.dimartin.servicecarrito.repository;

import com.dimartin.servicecarrito.dto.ProductoDTO;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name="productos", url="http://localhost:8080/productos")
public interface IProductoFeignRepository {
    
    @GetMapping("/getbycode/{cod}")
    public ProductoDTO getProdById(@PathVariable Long cod); 
    
    @GetMapping("/getall")
    public List<ProductoDTO> getAll();
    
    @GetMapping("/getnamebyid/{codigo}")
    public String getNameProdById(@PathVariable Long codigo);
    
    
    // METODOS PARA AGRAGAR Y SACAR STOCK PRODUCTOS AL AGREGAR Y SACAR PRODUCTOS DEL CARRITO
    @PutMapping("/reststock/{codigo}/{cantidad}")
    public ProductoDTO restStock(@PathVariable Long codigo, @PathVariable int cantidad);
    
    @PutMapping("/addstock/{codigo}/{cantidad}")
    public ProductoDTO addStock(@PathVariable Long codigo, @PathVariable int cantidad);
    
}
