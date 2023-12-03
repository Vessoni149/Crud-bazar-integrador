
package com.integrador.crudbazar.controller;

import com.integrador.crudbazar.model.Producto;
import com.integrador.crudbazar.service.IProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoController {
    @Autowired
    private IProductoService produServ;
    
    @GetMapping("/productos/traer")
    public List<Producto> getProductos(){
        List<Producto> listaProdu = produServ.getProductos();
        return listaProdu;
    }
    
     @GetMapping("/productos/traer/{id}")
    public Producto getProductos(@PathVariable Long id){
        Producto produ = produServ.getProducto(id);
        return produ;
    }
    
    @PostMapping("/productos/crear")
    public String saveProducto(@RequestBody Producto produ){
        produServ.saveProducto(produ);
        return "Producto creado";
    }
    
    @DeleteMapping("/productos/borrar/{id}")
    public String deleteProducto(@PathVariable Long id){
        produServ.deleteProducto(id);
        return "producto eliminado";
    }
    
    @PutMapping("/productos/editar/{id}")
    public Producto editProducto(@PathVariable Long id,
                                 @RequestParam(required=false, name="nombre") String nuevoNombre,
                                 @RequestParam(required=false, name="marca") String nuevaMarca,
                                 @RequestParam(required=false, name="costo") Double nuevoCosto,
                                 @RequestParam(required=false, name="cantidad_disponible") Double nuevaCantidadDisponible){
        Producto produ = this.getProductos(id);
        produ.setNombre(nuevoNombre);
        produ.setMarca(nuevaMarca);
        produ.setCosto(nuevoCosto);
        produ.setCantidad_disponible(nuevaCantidadDisponible);
        this.saveProducto(produ);
        return produ;
    }
    
    @GetMapping("productos/falta_stock")
    public List<Producto> getProductosFaltantes(){
        return produServ.getProductosFaltantes();
        
    }
    
}
