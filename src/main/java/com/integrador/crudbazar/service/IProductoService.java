package com.integrador.crudbazar.service;

import com.integrador.crudbazar.model.Producto;
import java.util.List;


public interface IProductoService {
    public List<Producto> getProductos();
    public Producto getProducto(Long id);
    public String saveProducto(Producto produ);
    public String deleteProducto(Long id);
    public Producto editProducto(Long id, String nuevoNombre, String nuevaMarca, Double nuevoCosto, Double nuevaCantidadDisponible);
    public List<Producto> getProductosFaltantes();
}
