package com.onloadtecnologia.www.Controller;


import java.util.Arrays;
import java.util.HashSet;



import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.onloadtecnologia.www.Models.User;
import com.onloadtecnologia.www.Repository.UserRepository;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.Claims;

import io.smallrye.jwt.build.Jwt;

@Path("user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @ConfigProperty(name="mp.jwt.verify.issuer")
    String issuer;

    @Inject
    UserRepository repository;
    
    @POST
    @Path("cadastro")
    @Transactional
    public User salvarUser(User user) {
        repository.persist(user);
        return user;
    }

    @POST
    @Path("login")
    @Transactional
    public String pesquisarUser(User user) {
        var userlogin = repository.findByEmailAndPassword(user.getEmail(),user.getPassword());
        String token = null;
        if(!userlogin.isEmpty()){
        token = Jwt.issuer(issuer)
                            .upn("jdoe@quarkus.io")
                            .groups(new HashSet<>(Arrays.asList("User")))
                            .claim(Claims.full_name.name(), userlogin.get().getNome() )
                            .claim(Claims.email.name(), userlogin.get().getEmail())
                            .sign();
        }
        return token;
    }

}
