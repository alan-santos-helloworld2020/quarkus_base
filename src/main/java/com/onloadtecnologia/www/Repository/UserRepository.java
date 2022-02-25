package com.onloadtecnologia.www.Repository;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import com.onloadtecnologia.www.Models.User;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    public Optional<User> findByEmailAndPassword(String email, String password)
    {
        Optional<User> user = find("email=?1 and password=?2",email,password).firstResultOptional();
        return  user; 
    }
    
}
