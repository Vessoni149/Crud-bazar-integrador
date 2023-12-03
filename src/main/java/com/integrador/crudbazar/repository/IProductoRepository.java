package com.integrador.crudbazar.repository;

import com.integrador.crudbazar.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long>{
   
}
