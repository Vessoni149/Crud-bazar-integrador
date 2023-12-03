package com.integrador.crudbazar.service;

import com.integrador.crudbazar.model.Cliente;
import com.integrador.crudbazar.repository.IClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService{
    @Autowired
    private IClienteRepository clienteRepo;
    
    public List<Cliente> getClientes(){
        List<Cliente> listaCli = clienteRepo.findAll();
        return listaCli;
    }

    @Override
    public Cliente getCliente(Long id) {
        Cliente cli = clienteRepo.findById(id).orElse(null);
        return cli;
    }

    @Override
    public String saveCliente(Cliente cli) {
        clienteRepo.save(cli);
        return "Cliente guardado";
    }

    @Override
    public String deleteCliente(Long id) {
        clienteRepo.deleteById(id);
        return "Cliente eliminado";
    }

    @Override
    public Cliente editCliente(Long id, String nuevoNombre, String nuevoApellido, String nuevoDni) {
        Cliente cli = this.getCliente(id);
        cli.setNombre(nuevoNombre);
        cli.setApellido(nuevoApellido);
        cli.setDni(nuevoDni);
        this.saveCliente(cli);
        return cli;
    }
    
}
