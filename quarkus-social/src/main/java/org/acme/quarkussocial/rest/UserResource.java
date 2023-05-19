package org.acme.quarkussocial.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.acme.quarkussocial.rest.dto.User;
import org.bson.types.ObjectId;

import java.net.URI;
import java.net.URISyntaxException;

@Path("/Users")
public class UserResource {

    @Inject
    UserRepository repository;

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") String id){
        User user = repository.findById(new ObjectId(id));
        return Response.ok(user).build();
    }

    @GET
    public Response get(){
        return Response.ok(repository.listAll()).build();
    }

    @GET
    @Path("/search/{name}")
    public Response search(@PathParam("name") String name){
        User user = repository.findByName(name);
        return user != null ? Response.ok(user).build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response create(User user) throws URISyntaxException {
        repository.persist(user);
        return Response.created(new URI("/"+user.id)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, User user){
        user.id = new ObjectId(id);
        repository.update(user);
        return Response.ok(user).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id){
        User user = repository.findById(new ObjectId(id));
        repository.delete(user);
        return Response.noContent().build();
    }

}
