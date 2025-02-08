 
package com.dimartin.servicecarrito.service;

import com.dimartin.servicecarrito.dto.CarritoDTO;
import com.dimartin.servicecarrito.dto.ProductoDTO;
import com.dimartin.servicecarrito.model.Carrito;
import com.dimartin.servicecarrito.repository.ICarritoRepository;
import com.dimartin.servicecarrito.repository.IProductoFeignRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarritoServiceImp implements ICarritoService{
    
    @Autowired
    private ICarritoRepository carritoRepo;
    
    @Autowired
    private IProductoFeignRepository prodFeignRepo;

    @Override
    public List<Carrito> getCarritos() {
        return carritoRepo.findAll();
    }

    @Override
    public Carrito getCarrito(Long id) {
        return carritoRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteCarrito(Long id) {
        carritoRepo.deleteById(id);
    }

    // SE CREA UN CARRO DE COMPRAS VACIO QUE PARA QUE POSTERIORMENTE SE LE PUEDAN AGREGAR Y QUITAR PRODUCTOS
    @Override
    public void saveCarrito(Carrito carr) {
        carritoRepo.save(carr);
    }
    

    // EDITAR CARRITO AGRAGANDO PRODUCTO
    @Override
    public Carrito editCarritoAdd(Long id, Long cod_prod, int cant) {
        
       // boolean added = false;
        
        Double total_carrito = 0.0;
        Double total_producto_agregado = 0.0;
        
        // 1- Traer el carrito al que se le va a agragar el producto
        Carrito carr = this.getCarrito(id);
        
        // 2- Traemos el producto que queremos agregar al carrito
        ProductoDTO prodDTO = prodFeignRepo.getProdById(cod_prod);
        
        // 3- OBTENER LA LISTA DE LOS ID DEL CARRITO
        List<Long> list_cods_prods = carr.getLista_cods_productos();
        
        // 4- VERIFICAMOS QUE EL PRODUCTO QUE QUEREMOS AGREGAR POSEA EL STOCK SUFICIENTE
 
        // RECIEN ME DOY CUENTA POR QUE NO FUNCIONA!!! 
        // SI CANTIDAD ES 1 Y HAY 3 UNIDADES EN STOK OBVIO QUE JAMAS VA A ENTRAR EN EL IF!!!
        if( cant <= prodDTO.getCantidad()){
           
            // 5- AGREGAMOS A LA LISTA DE IDS DEL CARRITO LA CANTIDAD DE VECES EL ID DEL PRODUCTO AGREGADO
            for(int i = 0; i < cant; i++ ){
                
                list_cods_prods.add(cod_prod);
         
            }
            
            prodFeignRepo.restStock(cod_prod, cant);
            
            // 6- CALCULAR EL VALOR DE LOS PRODUCTOS AGREGADOS AL CARRITO
            total_producto_agregado = prodDTO.getPrecio() * cant;
            
            // 7- SUMAR AL TOTAL DEL CARRITO EL VALOR DEL LOS PRODUCTOS NUEVOS AGREGADOS
            total_carrito = carr.getPrecio_total() + total_producto_agregado;
            
            carr.setPrecio_total(total_carrito);
            
          //  added = true;
        }
        
       // return added;
       
       carritoRepo.save(carr);
      
       return carr;
        
    }
    
    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    
    // EDITAR CARRITO DE COMPRAS QUITANDO UN TODOS LOS PRODUCTOS DE UN MISMO CODIGO
    @Override
    public boolean editCarritoRemoveAll(Long id, Long cod_prod, int cant) {
        
        boolean removed = false;
        
        int cont = 0;
       
        Double total = 0.0;

        // 1- TRAERMOS AL CARRITO AL QUE SE LE VA A QUITAR EL PRODUCTO POR SU ID
        Carrito carr = this.getCarrito(id);

        // 2- SE CREA UNA LISTA QUE CONTENGA CADA UNO DE LOS CODIGOS DE LOS PRODUCTOS DEL CARRITO
        List<Long> lista_cod_prods = carr.getLista_cods_productos(); 
        
    
        // 3- CREAR UNA LISTA QUE CONTENGA CADA UNO DE LOS PRODUCTOS DEL CARRITO
        List<ProductoDTO> lista_prods = new ArrayList<>();

        for(int i= 0; i < lista_cod_prods.size(); i++){

            if(Objects.equals(cod_prod, lista_cod_prods.get(i))){

                cont++;
                System.out.println("El valor de cont es : " + cont);

            }

        }    
        // AHORA VAMOS A VER SI LA CANTIDAD QUE QUIERO ELIMINAR COINCIDE CON EL CONTADOR
        if(cont == cant){

            System.out.println("La cantidad es : " + cant + " Y el contador es : " + cont);

            for(int i = 0; i < cant; i++){
                lista_cod_prods.remove(cod_prod); 
            }
            
            prodFeignRepo.addStock(cod_prod, cant);

            // 5- LLENAMOS LA LISTA DE PRODUCTOS DEL CARRITO RECORRIENDO LA LISTA DE LOS CODIGOS DE LOS MISMOS
            for(Long cod : lista_cod_prods){            
                lista_prods.add(prodFeignRepo.getProdById(cod));
            }   

            // 6- RECORREMOS LA LISTA DE PRODUCTOS DEL CARRITO Y SUMAMOS LOS PRECIOS DE CADA UNO AL TOTAL
            for(ProductoDTO prod : lista_prods){

                total += prod.getPrecio();
            }

            // 7- SETEAMOS AL CARRITO EL PRECIO NUEVO Y LA LISTA DE CODIGOS NUEVA Y LA GUARDAMOS EN LA BBDD
            carr.setPrecio_total(total);
            carr.setLista_cods_productos(lista_cod_prods);

            carritoRepo.save(carr);

            removed = true;        
        }
        return removed;        
    }
    
    
    // METODO PARA TRAER LOS PRODUCTOS DE UN CARRITO DESDE LA API DE PRODUCTOS
    public List<ProductoDTO> getAllPrductosByCarrId(Long cod_carr){
        
        return prodFeignRepo.getAll();
    
    }
    
    // METODO PARA TRAER UN CARRITO DTO POR SU ID
    @Override
    public CarritoDTO getCarrDtoById(Long cod_carr){
        
        //ACA TENGO Q CONSEGUIR EL NOMBRE DE TODOS LOS PRODUCTOS DEL CARRITO
        //carrDTO.setLista_productos(carr.getCodigo_lista_productos());
        
        CarritoDTO carrDTO = new CarritoDTO();
        
        Carrito carr = this.getCarrito(cod_carr);
        
        // CREO E INSTANCIO LA LISTA EN LA QUE SE VAN A ALMACENAR LOS PRODUCTOS POR SU NOMBRE 
        // PARA LUEGO SETEARLA AL CARRITO DTO
        List<String>list_nomb_prods = new ArrayList<>();
        
        List<Long> list_ids_prods = carr.getLista_cods_productos();
        
        // ACA TENGO QUE RECORRER LA LISTA DE LOS IDS Y EN CADA CICLO LLAMAR A LA API QUE
        // ME DE DEVUELVA EL NOMBRE DEL PRODUCTO Y AGREGARLO A LA LISTA DE NOMBRES Y SETEARLO AL CARR DTO
         
        for(Long id : list_ids_prods){
            list_nomb_prods.add(prodFeignRepo.getNameProdById(id));
        }
        
        carrDTO.setPrecio_total(carr.getPrecio_total());
        carrDTO.setLista_productos(list_nomb_prods);
        
        return carrDTO;
        
    }

    // METODO PARA ELIMINAR DE A UN PRODUCTO O MAS PERO NO TODOS
    @Override
    public boolean editCarritoRemove(Long id, Long cod_prod, int cant) {
        
        boolean removed = false;
        
        int cont = 0;
        Double total = 0.0;
//
//        // 1- TRAERMOS AL CARRITO AL QUE SE LE VA A QUITAR EL PRODUCTO POR SU ID
        Carrito carr = this.getCarrito(id);
//
//      // 2- SE CREA UNA LISTA QUE CONTENGA CADA UNO DE LOS CODIGOS DE LOS PRODUCTOS DEL CARRITO
        List<Long> lista_cod_prods = carr.getLista_cods_productos(); 
//        
//      // 3- CREAR UNA LISTA QUE CONTENGA CADA UNO DE LOS PRODUCTOS DEL CARRITO
        List<ProductoDTO> lista_prods = new ArrayList<>();
//
        for(int i= 0; i < lista_cod_prods.size(); i++){

            if(Objects.equals(cod_prod, lista_cod_prods.get(i))){

                cont++;
                System.out.println("El valor de cont es : " + cont);

            }

        }    
//      // AHORA VAMOS A VER SI LA CANTIDAD QUE QUIERO ELIMINAR COINCIDE CON EL CONTADOR

        if(cant < cont || cant == 1){
            
            for(int i = 0; i < cant; i++){
                System.out.println("Dentro del for");
                lista_cod_prods.remove(cod_prod); 
                // LLAMAMOS A LA API DE PRODUCTOS PARA QUE ACTUALIZE EL STOCK 
                prodFeignRepo.addStock(cod_prod, cant);
            }
           

            // 5- LLENAMOS LA LISTA DE PRODUCTOS DEL CARRITO RECORRIENDO LA LISTA DE LOS CODIGOS DE LOS MISMOS
            for(Long cod : lista_cod_prods){            
                lista_prods.add(prodFeignRepo.getProdById(cod));
            }   

            // 6- RECORREMOS LA LISTA DE PRODUCTOS DEL CARRITO Y SUMAMOS LOS PRECIOS DE CADA UNO AL TOTAL
            for(ProductoDTO prod : lista_prods){

                total += prod.getPrecio();
            }

            // 7- SETEAMOS AL CARRITO EL PRECIO NUEVO Y LA LISTA DE CODIGOS NUEVA Y LA GUARDAMOS EN LA BBDD
            carr.setPrecio_total(total);
            carr.setLista_cods_productos(lista_cod_prods);

            carritoRepo.save(carr);

            removed = true;        
        }
        
        return removed;        
        
    }
    
}
