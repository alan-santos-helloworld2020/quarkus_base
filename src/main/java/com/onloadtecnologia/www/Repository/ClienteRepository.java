package com.onloadtecnologia.www.Repository;

import javax.enterprise.context.ApplicationScoped;

import com.onloadtecnologia.www.Models.Cliente;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente>{
    
}
