package com.integrador.crudbazar.service;

import com.integrador.crudbazar.model.Producto;
import com.integrador.crudbazar.repository.IProductoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService{
    @Autowired
    private IProductoRepository productoRepo;

    @Override
    public List<Producto> getProductos() {
        List<Producto> listaProductos = productoRepo.findAll();
        return listaProductos;
    }

    @Override
    public Producto getProducto(Long id) {
       Producto produ = productoRepo.findById(id).orElse(null);
       return produ;
    }

    @Override
    public String saveProducto(Producto produ) {
        productoRepo.save(produ);
        return "Producto creado";
    }

    @Override
    public String deleteProducto(Long id) {
     productoRepo.deleteById(id);
     return "Producto eliminado";
    }

    @Override
    public Producto editProducto(Long id, String nuevoNombre, String nuevaMarca,
            Double nuevoCosto, Double nuevaCantidadDisponible) {
        Producto produ = this.getProducto(id);
        produ.setNombre(nuevoNombre);
        produ.setMarca(nuevaMarca);
        produ.setCosto(nuevoCosto);
        produ.setCantidad_disponible(nuevaCantidadDisponible);
        this.saveProducto(produ);
        return produ;
    }

    @Override
    public List<Producto> getProductosFaltantes() {
        List<Producto> listaProductos = productoRepo.findAll();
        List<Producto> listaProduFaltantes = new ArrayList();
        for(Producto produ : listaProductos){
            if(produ.getCantidad_disponible() < 5){
                listaProduFaltantes.add(produ);
            }
        }
        return listaProduFaltantes;
    }
}
