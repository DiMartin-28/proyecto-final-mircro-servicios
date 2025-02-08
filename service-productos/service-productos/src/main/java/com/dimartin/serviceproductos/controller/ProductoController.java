package com.dimartin.serviceproductos.controller;

import com.dimartin.serviceproductos.model.Producto;
import com.dimartin.serviceproductos.service.ProductoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.dimartin.serviceproductos.service.IProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoServ;
    
    @Autowired
    private ProductoServiceImp prodSerImp;

    @GetMapping("/getall")
    public List<Producto> getProductos(){

        return productoServ.getProductos();
    }

    @PostMapping("/save")
    public String guardarProducto(@RequestBody Producto producto){

        productoServ.saveProducto(producto);
        return "Producto creado correctamente!";
        
    }
    
    @PutMapping("/edit/{codigo}")
    public Producto editProducto(@PathVariable Long codigo, @RequestBody Producto prod){
    
        productoServ.editProducto(codigo, prod);
        Producto prod_editado = productoServ.getProducto(codigo);
        return prod_editado;
        
    }
    
    @DeleteMapping("/delete/{codigo}")
    public String deleteProd(@PathVariable Long codigo){
        
        productoServ.deleteProducto(codigo);
        return "Producto eliminado correctamente de la BB.DD!";
        
    }
    
    
    // PROBANDO EL METODO getProductByCode 
    @GetMapping("/getbycode/{codigo}")
    public Producto getProductByCod(@PathVariable Long codigo){
    
        return prodSerImp.getProductById(codigo);
        
    }
    
    // METODO QUE DEVUELVE EL NOMBRE DE UN PRODUCTO POR SI ID
    // QUE SE VA A CONSUMIR DESDE LA API DE CARRITO
    @GetMapping("/getnamebyid/{codigo}")
    public String nameProduct(@PathVariable Long codigo){
        return productoServ.getNameProdById(codigo);
    }
    
    // METODOS QUE AGRAGAR Y SACAN STOCK A UN PRODUCTO 
    // QUE SERAN CONSUMIDO DESDE LA API DE CARRITO
    
    @PutMapping("/addstock/{codigo}/{cantidad}")
    public Producto addStock(@PathVariable Long codigo, @PathVariable int cantidad){
    
        return productoServ.editAddStock(codigo, cantidad);
    }
    
    @PutMapping("/reststock/{codigo}/{cantidad}")
    public Producto restStock(@PathVariable Long codigo, @PathVariable int cantidad){
    
        return productoServ.editRestStock(codigo, cantidad);
    }
}
