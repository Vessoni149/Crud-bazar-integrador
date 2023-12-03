package com.integrador.crudbazar.service;

import com.integrador.crudbazar.dto.VentaDto;
import com.integrador.crudbazar.model.Producto;
import com.integrador.crudbazar.model.Venta;
import java.time.LocalDate;
import java.util.List;


public interface IVentaService {
    public List<Venta> getVentas();
    public Venta getVenta(Long id);
    public String saveVenta(Venta venta);
    public String deleteVente(Long id);
    public String editVenta(Long id, LocalDate nuevaFechaVenta, Double nuevoTotal);
    public List<Producto> getProductosVendidos(Long id);
    public String getTotalMontoVentas(LocalDate fecha_venta);
    public VentaDto getMayorVenta();
}
