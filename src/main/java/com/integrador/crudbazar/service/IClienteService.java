package com.integrador.crudbazar.service;

import com.integrador.crudbazar.model.Cliente;
import java.util.List;


public interface IClienteService {
     public List<Cliente> getClientes();
     public Cliente getCliente(Long id);
     public String saveCliente(Cliente cli);
     public String deleteCliente(Long id);
     public Cliente editCliente(Long id,String nuevoNombre, String nuevoApellido, String dni);
     
}
