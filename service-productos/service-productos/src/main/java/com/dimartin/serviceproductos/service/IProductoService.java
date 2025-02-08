package com.dimartin.serviceproductos.service;

import com.dimartin.serviceproductos.model.Producto;

import java.util.List;

public interface IProductoService {

    public List<Producto> getProductos();

    public void deleteProducto(Long id);

    public void saveProducto(Producto producto);

    public Producto getProducto(Long id);

    public Producto editProducto(Long id, Producto producto);
    
    // AGREGAR EL METODO GET NOMBRE PRODUCTO BY ID
    public String getNameProdById(Long id);
    
    // METODOS PARA AGREGAR Y SACAR PRODUCTOS DEL STOCK
    public Producto editAddStock(Long cod, int cant);
    
    public Producto editRestStock(Long cod, int cant);

}
