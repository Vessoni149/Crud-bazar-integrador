
package com.integrador.crudbazar.controller;

import com.integrador.crudbazar.dto.VentaDto;
import com.integrador.crudbazar.model.Cliente;
import com.integrador.crudbazar.model.Producto;
import com.integrador.crudbazar.model.Venta;
import com.integrador.crudbazar.service.IVentaService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VentaController {
    @Autowired
    private IVentaService ventaServ;
    
    @GetMapping("/ventas/traer")
    public List<Venta> getVentas(){
        List<Venta> listaVentas = ventaServ.getVentas();
        return listaVentas;
    }
    
    @GetMapping("/ventas/traer/{id}")
    public Venta getVentas(@PathVariable Long id){
        Venta venta = ventaServ.getVenta(id);
        return venta;
    }
    
    @PostMapping("/ventas/crear")
    public String saveVenta(@RequestBody Venta venta){
        ventaServ.saveVenta(venta);
        return "Venta creada";
    }
    
    @DeleteMapping("/ventas/borrar/{id}")
    public String deleteVenta(@PathVariable Long id){
        ventaServ.deleteVente(id);
        return "Venta eliminada";
    } 
    
    @PutMapping("/ventas/editar/{id}")
    public Object editVenta(@PathVariable Long id,
                           @RequestBody Venta ventaNueva){
        Venta venta = ventaServ.getVenta(id);
        if(venta.getCodigo_venta() == ventaNueva.getCodigo_venta()){
            ventaServ.saveVenta(ventaNueva);
        }else{
            return "Los ids no coinciden";
        }
        
        return venta;
    }
   
    @GetMapping("/ventas/productos/{id}")
    public List<Producto> getProductosVendidos(@PathVariable Long id){
        return ventaServ.getProductosVendidos(id);
    }
    
    @GetMapping("/ventas/fecha-cantidad")
    public String getTotalMontoVentas(@RequestParam(name="fecha_venta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha_venta){
        return ventaServ.getTotalMontoVentas(fecha_venta);
        
    }
    
    @GetMapping("/ventas/mayor_venta")
    public VentaDto getMayorVenta(){
        return  ventaServ.getMayorVenta();
    }
    
}
