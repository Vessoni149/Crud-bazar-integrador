
package com.integrador.crudbazar.controller;

import com.integrador.crudbazar.model.Cliente;
import com.integrador.crudbazar.service.IClienteService;
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
public class ClienteController {
    
    @Autowired
    private IClienteService cliServ;
    
    @PostMapping("/clientes/crear")
    public String saveCliente(@RequestBody Cliente cli){
        cliServ.saveCliente(cli);
        return "Cliente creado";
    }
    
    @GetMapping("/clientes/traer")
    public List<Cliente> getClientes(){
        List<Cliente> listaCli = cliServ.getClientes();
        return listaCli;
    }
    
    @GetMapping("/clientes/traer/{id}")
    public Cliente getCliente(@PathVariable Long id){
        Cliente cli = cliServ.getCliente(id);
        return cli;
    }
    
    @DeleteMapping("/clientes/borrar/{id}")
    public String deleteCliente(@PathVariable Long id){
        cliServ.deleteCliente(id);
        return "Cliente eliminado";
    }
    
    @PutMapping("/clientes/editar/{id}")
    public Cliente editCliente(@PathVariable Long id,
                              @RequestParam(required=false, name="nombre") String nuevoNombre,
                              @RequestParam(required=false, name="apellido") String nuevoApellido,
                              @RequestParam(required=false, name="dni") String nuevoDni){
        cliServ.editCliente(id, nuevoNombre, nuevoApellido, nuevoDni);
        Cliente cli = cliServ.getCliente(id);
        return cli;
    }
}
