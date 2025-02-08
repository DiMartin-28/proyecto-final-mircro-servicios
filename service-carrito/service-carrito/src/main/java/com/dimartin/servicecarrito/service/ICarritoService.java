
package com.dimartin.servicecarrito.service;

import com.dimartin.servicecarrito.dto.CarritoDTO;
import com.dimartin.servicecarrito.model.Carrito;
import java.util.List;


public interface ICarritoService {
    
    public List<Carrito> getCarritos();
    
    public Carrito getCarrito(Long id);
    
    public void deleteCarrito(Long id);
    
    public void saveCarrito(Carrito carr);
    
    public Carrito editCarritoAdd(Long id, Long cod_prod, int cant);
    
    public boolean editCarritoRemove(Long id, Long cod_prod, int cant);
    
    public boolean editCarritoRemoveAll(Long id, Long cod_prod, int cant);
    
    // METODO PARA CREAR UN CARRITO DTO CONSUMIENDO LA API DE PRODUCTOS
    public CarritoDTO getCarrDtoById(Long cod_carr);
    

    
}
