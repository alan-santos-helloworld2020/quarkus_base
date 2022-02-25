package com.onloadtecnologia.www;


import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.onloadtecnologia.www.Models.Cliente;
import com.onloadtecnologia.www.Repository.ClienteRepository;
import java.util.List;

@Path("/clientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GreetingResource {

    @Inject
    ClienteRepository repository;

    @GET
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = repository.listAll();
        return clientes;
    }

    @GET
    @Path("/{id}")
    public Cliente pesquisarClientes(@PathParam("id") Long id) {
        var clientes = repository.findById(id);
        return clientes;
    }

    @POST
    @Transactional
    public Cliente salvarCliente(Cliente cliente) {
        repository.persist(cliente);
        if (repository.isPersistent(cliente)) {
            return cliente;
        } else {
            return cliente;
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Cliente editarCliente(@PathParam("id") Long id,Cliente cliente) {
       var old = repository.findById(id);
       if(old == null){
        throw new NotFoundException();
       } 
       old.setNome(cliente.getNome());
       old.setTelefone(cliente.getTelefone());
       old.setEmail(cliente.getEmail());
       old.setCep(cliente.getCep());
       repository.persist(old);
       return cliente;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Cliente deletarCliente(@PathParam("id") Long id){
        var del  = repository.findById(id);
        repository.delete(del);
        return del;
    }
}