package com.redhat.emergency.response.responder.rest;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.redhat.emergency.response.responder.model.Responder;
import com.redhat.emergency.response.responder.repository.ResponderRepository;

@Path("responders")
public class ResponderResource {

    @Inject
    ResponderRepository repository;

    @DELETE
    @Transactional
    public Response delete() {
        repository.deleteAll();
        return Response.status(204).build();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(List<Responder> responders) {
        repository.persist(responders.stream().map(r -> repository.toEntity(r)));
        return Response.status(201).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Responder> get() {
        return repository.findAll().list().stream().map(e -> repository.fromEntity(e)).collect(Collectors.toList());
    }

}
