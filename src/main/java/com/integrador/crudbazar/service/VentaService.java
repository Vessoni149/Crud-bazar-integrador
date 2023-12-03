package com.integrador.crudbazar.service;

import com.integrador.crudbazar.dto.VentaDto;
import com.integrador.crudbazar.model.Producto;
import com.integrador.crudbazar.model.Venta;
import com.integrador.crudbazar.repository.IVentaRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService{
    @Autowired
    private IVentaRepository ventaRepo;

    @Override
    public List<Venta> getVentas() {
        List<Venta> listaVentas = ventaRepo.findAll();
        return listaVentas;
    }

    @Override
    public Venta getVenta(Long id) {
        Venta venta = ventaRepo.findById(id).orElse(null);
        return venta;
    }

    @Override
    public String saveVenta(Venta venta) {
        List<Producto> productosDeLaVenta = venta.getListaProductos();
        Double total = 0.0;
        
        for(Producto produ : productosDeLaVenta){
            total += produ.getCosto();
        }

        venta.setTotal(total);
        ventaRepo.save(venta);
        return "Venta creada";
    }

    @Override
    public String deleteVente(Long id) {
        ventaRepo.deleteById(id);
        return "venta eliminada";
    }

    @Override
    public String editVenta(Long id, LocalDate nuevaFechaVenta,
            Double nuevoTotal) {
        Venta venta = this.getVenta(id);
        venta.setFecha_venta(nuevaFechaVenta);
        List<Producto> productosDeLaVenta = venta.getListaProductos();
        Double total = 0.0;
        
        for(Producto produ : productosDeLaVenta){
            total += produ.getCosto();
        }

        venta.setTotal(total);
        this.saveVenta(venta);
        return "Venta editada";
    }

    @Override
    public List<Producto> getProductosVendidos(Long id) {
        Venta venta = this.getVenta(id);
        List<Producto> listaProductos = venta.getListaProductos();
        return listaProductos;
    }

    @Override
    public String getTotalMontoVentas(LocalDate fecha_venta) {
        List<Venta> listaVentas = ventaRepo.findAll();
        List<Venta> ventasMismoDia = new ArrayList();
        int cantidadVentas=0;
        double totalIngresado=0;
        for(Venta venta : listaVentas){
            if(venta.getFecha_venta().equals(fecha_venta)){
                ventasMismoDia.add(venta);
                cantidadVentas++;
                totalIngresado += venta.getTotal();
            }
        }
        return "El día " + fecha_venta + " se realizaron " + cantidadVentas + " ventas, por un total de " + totalIngresado;
        
    }

    @Override
    public VentaDto getMayorVenta() {
        
        //Obtener la Venta de mayor monto:
        List<Venta> listaVentas = ventaRepo.findAll();
        ArrayList<Double> montos = new ArrayList();
        for(Venta venta: listaVentas){
            montos.add(venta.getTotal());
        }
        Double montoMaximo = Collections.max(montos);
        
        VentaDto ventaDto = new VentaDto();
        ventaDto.setTotal(montoMaximo);
        
        Long codigo_venta = 0L;
        int cantidad_productos = 0;
        String nombre = "";
        String apellido = "";
        for(Venta venta : listaVentas){
            if(venta.getTotal().equals(montoMaximo)){
               codigo_venta = venta.getCodigo_venta();
               cantidad_productos = venta.getListaProductos().size();
               nombre = venta.getUnCliente().getNombre();
               apellido = venta.getUnCliente().getApellido();
            }
        }
        ventaDto.setCodigo_venta(codigo_venta);
        ventaDto.setCantidad_productos(cantidad_productos);
        ventaDto.setNombre_cliente(nombre);
        ventaDto.setApellido_cliente(apellido);
 
        return ventaDto;
    }
}
