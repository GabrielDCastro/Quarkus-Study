package org.acme.quarkussocial.rest;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.quarkussocial.rest.dto.User;

import java.util.List;

@ApplicationScoped
public class UserRepository implements PanacheMongoRepository<User> {

    public User findByName(String name){
        return find("name", name).firstResult();
    }
    public List<User> findOrderedname(){
        return listAll(Sort.by("name"));
    }
}
