
package com.dimartin.serviceventa.service;

import com.dimartin.serviceventa.dto.CarritoDTO;
import com.dimartin.serviceventa.dto.VentaDTO;
import com.dimartin.serviceventa.model.Venta;
import com.dimartin.serviceventa.repository.ICarritoFeignRepository;
import com.dimartin.serviceventa.repository.IVentaRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaServiceImp implements IVentaService{
    
    @Autowired
    private IVentaRepository ventaRepo;
    
    @Autowired
    private ICarritoFeignRepository carriRepo;

    @Override
    public List<Venta> getVentas() {
        return ventaRepo.findAll();
    }

    @Override
    public Venta getVenta(Long codigo) {
        return ventaRepo.findById(codigo).orElse(null);
    }

    @Override
    public void deleteVenta(Long codigo) {
        ventaRepo.deleteById(codigo);
    }

     @Override
    public Venta saveVentaSimpl(Venta venta) {
        return ventaRepo.save(venta);
    }
    
   
    @Override
    public void editVenta(Venta venta, Long codigo) {
        
        Venta ven = this.getVenta(codigo);
        
        ven.setFecha(venta.getFecha());
        
        ventaRepo.save(ven);
        
    } 

    // PROBAR SACANDO LA VARIABLE FECHA EN ESTE, EL REPOSOTORIO Y EL CONTROLADOR Y POSTMAN Y
    //  v.setFecha(fecha); CAMBIAR A  v.setFecha(LocalDate.now());
    @Override
    public Venta saveRealVenta(Long cod_carr) {
        
        Venta v = new Venta();
    
        CarritoDTO carr = carriRepo.getCarrDtoById(cod_carr);

                v.setFecha(LocalDate.now());
                v.setCod_carr(cod_carr);
                v.setTotal(carr.getPrecio_total());
                ventaRepo.save(v);
 
        return v;
    }
    
    // 15-03-2024 AHORA TENGO QUE HACER UN METODO PARA QUE ME MUESTRA UNA VENTA DTO AL CLIENTE
    // TENGO QUE USAR EL METODO FEIGN GETCARRITO DTO
    
    @Override 
    public VentaDTO getVentaDto(Long codigo){
        
        VentaDTO vDto = new VentaDTO();
        
        Venta v = this.getVenta(codigo);
        
        Long cod_carr = v.getCod_carr();
        
        CarritoDTO carr = carriRepo.getCarrDtoById(cod_carr);
        
        if(v.getCodigo().equals(codigo)){
        
            vDto.setFecha(v.getFecha());
            vDto.setTotal(v.getTotal());
            vDto.setLista_nombre_productos(carr.getLista_productos());
            
        }
        
        return vDto;
        
    }

}
