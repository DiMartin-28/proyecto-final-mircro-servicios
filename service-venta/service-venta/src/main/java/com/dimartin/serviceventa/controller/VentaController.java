 
package com.dimartin.serviceventa.controller;

import com.dimartin.serviceventa.dto.CarritoDTO;
import com.dimartin.serviceventa.dto.VentaDTO;
import com.dimartin.serviceventa.model.Venta;
import com.dimartin.serviceventa.repository.ICarritoFeignRepository;
import com.dimartin.serviceventa.service.IVentaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ventas")
public class VentaController {
    
    @Autowired
    private IVentaService ventaService;
    
    @Autowired
    private ICarritoFeignRepository carrRepo;
    
    @GetMapping("/getall")
    public List<Venta> getVentas(){
        
        return ventaService.getVentas();
    }
    
    @PostMapping("/save")
    public String saveVenta(@RequestBody Venta v){
        
        ventaService.saveVentaSimpl(v);
        
        return "Venta guardada exitosamente en la BB.DD!";
    
    }
    
    @GetMapping("/getbyid/{codigo}")
    public Venta getVentaById(@PathVariable Long codigo){
        return ventaService.getVenta(codigo);
    }
    
    // PROBANDO LA LLAMADA A LA API DE CARRITOS PARA TRAER TODOS LOS CARRITOS
    @GetMapping("/carrito/getAll")
    public List<CarritoDTO>getAllCarrs(){
        return carrRepo.getAllCarritos();
    }
    
    
    // PROVANDO EL METODO saveRealVenta
    @PostMapping("/saver/{cod_carr}")
    public Venta saveRealVenta(@PathVariable Long cod_carr){
        Venta v = ventaService.saveRealVenta(cod_carr);
        return v;
    }
    
    // METODO QUE RETORNA UNA VENTA DTO PARA MOSTAR AL CLIENTE
    
    @GetMapping("/getventadto/{codigo}")
    public VentaDTO getVentaDtoById(@PathVariable Long codigo){
    
        VentaDTO v = ventaService.getVentaDto(codigo);
        
        return v;
    }
    
}
