package com.dimartin.serviceproductos.service;

import com.dimartin.serviceproductos.model.Producto;
import com.dimartin.serviceproductos.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImp implements IProductoService{

    @Autowired
    private IProductoRepository productoRepo;

    @Override
    public List<Producto> getProductos() {
        return productoRepo.findAll();
    }

    @Override
    public void deleteProducto(Long id) {
        productoRepo.deleteById(id);
    }


    @Override
    public void saveProducto(Producto producto) {
        productoRepo.save(producto);
    }

    @Override
    public Producto getProducto(Long id) {
        return productoRepo.findById(id).orElse(null);
    }

    @Override
    public Producto editProducto(Long id, Producto producto) {

        Producto p = this.getProducto(id);
        
        p.setNombre(producto.getNombre());
        p.setMarca(producto.getMarca());
        p.setPrecio(producto.getPrecio());
        p.setCantidad(producto.getCantidad());
        
        productoRepo.save(p);
        
        return p;
    }
    
    
    // METODO CONSULADO DESDE LA API DE CARRITO
    public Producto getProductById(Long codigo){
        
        List<Producto> list_productos = this.getProductos();
        
        Producto prod = new Producto();
        
        for(Producto p : list_productos){
            
            if(p.getCodigo().equals(codigo)){
                
                prod = p;
                return prod;
                
            }
            
        }
        
        return null;
    }

    // METODO QUE SE VA A CONSULTAR DESDE LA API DE CARRITO PARA OBTENER LA LISTA DE LOS NOMBRE DE SUS PRODUCOS
    @Override
    public String getNameProdById(Long codigo) {
        
        String name_prod = null;
         
        Producto prod = this.getProducto(codigo);
        name_prod = prod.getNombre();
        
        return name_prod;
        
    }

    @Override
    public Producto editAddStock(Long cod, int cant) {
        
        Producto p = this.getProducto(cod);
        p.setCantidad(p.getCantidad() + cant);
        this.saveProducto(p);
        
        return p;
    }

    @Override
    public Producto editRestStock(Long cod, int cant) {
        
        Producto p = this.getProducto(cod);
        p.setCantidad(p.getCantidad() - cant);
        this.saveProducto(p);
        
        return p;
    }
}
