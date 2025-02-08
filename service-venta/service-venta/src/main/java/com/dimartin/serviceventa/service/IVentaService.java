
package com.dimartin.serviceventa.service;

import com.dimartin.serviceventa.dto.VentaDTO;
import com.dimartin.serviceventa.model.Venta;
import java.util.List;


public interface IVentaService {
    
    public List<Venta> getVentas();
    
    public Venta getVenta(Long codigo);
    
    public void deleteVenta(Long codigo);
    
    public Venta saveVentaSimpl(Venta venta);
    
    // GUARDAR VENTA AUTENTICA
    public Venta saveRealVenta(Long cod_carr);
    
    public void editVenta(Venta venta, Long codigo);
    
    public VentaDTO getVentaDto(Long codigo); 
    
    
    
}
