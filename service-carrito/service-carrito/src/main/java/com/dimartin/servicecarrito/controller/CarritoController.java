
package com.dimartin.servicecarrito.controller;

import com.dimartin.servicecarrito.dto.CarritoDTO;
import com.dimartin.servicecarrito.dto.ProductoDTO;
import com.dimartin.servicecarrito.model.Carrito;
import com.dimartin.servicecarrito.repository.IProductoFeignRepository;
import com.dimartin.servicecarrito.service.ICarritoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carrito")
public class CarritoController {
    
    @Autowired
    private ICarritoService carritoServ;
    
    @Autowired
    private IProductoFeignRepository prodFeignRepo;
    
    @GetMapping("/getall")
    public List<Carrito> getCarritos(){
        
        return carritoServ.getCarritos();
        
    }
    
    
    @PostMapping("/save")
    public String createCarrito(@RequestBody Carrito carr){
    
        carritoServ.saveCarrito(carr);
        
        return "Carrito de compras creado correctamente!";
    }
    
    
    // TRAYENDO LOS PRODUCTOS MEDIANTE FEIGN
    @GetMapping("/getallproductos")
    public List<ProductoDTO> getAllProductosFeign(){
        
        return prodFeignRepo.getAll();
        
    }
    
    // ENDPOINT PARA ACTUALIZAR CARRITO AGRAGANDO PRODUCTOS
    
//    @PutMapping("/updateadd/{id_carr}/{id_prod}/{cant_prod}")
//    public String updateCarrAdd(@PathVariable Long id_carr, @PathVariable Long id_prod, @PathVariable int cant_prod){
//        
//        String mensaje = "Ocurrió un error al agragar productos al carrtito!";
//        
//        if(carritoServ.editCarritoAdd(id_carr, id_prod, cant_prod)){
//            mensaje = "Carrito actualizado correctamente!";
//        }
//        
//        return mensaje;
//    }
    
    @PutMapping("/updateadd/{id_carr}/{id_prod}/{cant_prod}")
    public Carrito updateCarrAdd(@PathVariable Long id_carr, @PathVariable Long id_prod, @PathVariable int cant_prod){
        
        Carrito carr = carritoServ.editCarritoAdd(id_carr, id_prod, cant_prod);
           
        
        return carr;
    }
    
    
    // ENDPOINT PARA ACTUALIZAR CARRITO QUITANDO TODOS LOS PRODUCTOS
    @PutMapping("/updateremoveall/{id_carr}/{id_prod}/{cant_prod}")
    public String updateCarrRemove(@PathVariable Long id_carr, @PathVariable Long id_prod, @PathVariable int cant_prod){
        
        String mensaje = "Huvo un error al quitar el producto del carrito, "
                + "por favor seleccione el producto que desea eliminar";
        
        if(carritoServ.editCarritoRemoveAll(id_carr, id_prod, cant_prod)){
        
            mensaje = "Carrito actualizado correctamene!";
        }
        
        return mensaje;
    }
    
    // ENDPOINT PARA ACTUALIZAR EL CARRITO SACANDO DE A UN PRODUCTO O MAS PERO NO A TODOS
    //editCarritoRemove(Long id, Long cod_prod, int cant)
    
    @PutMapping("updateremove/{id_carr}/{cod_prod}/{cant}")
    public String editCarritoRemove(@PathVariable Long id_carr,@PathVariable Long cod_prod, @PathVariable int cant){
        
        String mensaje = "Huvo un error al quitar productos del carrito, "
                + " por favor seleccione el producto que desea eliminar";
        
        if(carritoServ.editCarritoRemove(id_carr, cod_prod, cant)){
            mensaje = "Carrito actualizado correctamente!"; 
        }
        
        return mensaje;
    
    }
    
    @GetMapping("/getallprods")
    public List<ProductoDTO> getAllPrductosByCarrId(){
    
        return prodFeignRepo.getAll();
    }
    
    @GetMapping("/getbycode/{cod}")
    public Carrito getCarritoByCod(@PathVariable Long cod){
        
        return carritoServ.getCarrito(cod);
        
    }
    
    // EDPOINT PARA MOSTRAR UN CARRITO DTO POR SU ID
    
    @GetMapping("/getnamebyid/{codigo}")
    public CarritoDTO getCarrDTOById(@PathVariable Long codigo){
        
        return carritoServ.getCarrDtoById(codigo);
        
    }
    
}
