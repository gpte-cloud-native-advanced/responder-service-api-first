package com.redhat.emergency.response.responder.rest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.redhat.emergency.response.responder.model.Responder;
import com.redhat.emergency.response.responder.repository.ResponderEntity;
import com.redhat.emergency.response.responder.repository.ResponderRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Parameters;

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
    public Response get(@QueryParam("limit") Optional<Integer> limit, @QueryParam("offset") Optional<Integer> offset) {
        PanacheQuery<ResponderEntity> query = repository.findAll();
        if (limit.isPresent() && offset.isEmpty()) {
            query = query.page(0, limit.get());
        } else if (limit.isPresent()) {
            query = query.page(offset.get(), limit.get());
        }
        return Response.ok(query.list().stream().map(e -> repository.fromEntity(e)).collect(Collectors.toList())).build();
    }

    @GET
    @Path("/filter/available")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAvailable(@QueryParam("limit") Optional<Integer> limit, @QueryParam("offset") Optional<Integer> offset) {
        PanacheQuery<ResponderEntity> query =
                repository.find("SELECT r FROM ResponderEntity r WHERE r.available = :available", Parameters.with("available", true));
        if (limit.isPresent() && offset.isEmpty()) {
            query = query.page(0, limit.get());
        } else if (limit.isPresent()) {
            query = query.page(offset.get(), limit.get());
        }
        return Response.ok(query.list().stream().map(e -> repository.fromEntity(e)).collect(Collectors.toList())).build();
    }

    @GET
    @Path("/{responderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("responderId") Long responderId) {
       Optional<ResponderEntity> responder = repository.findByIdOptional(responderId);
       if (responder.isPresent()) {
           return Response.ok(repository.fromEntity(responder.get())).build();
       } else {
           return Response.status(404).build();
       }
    }

    @PUT
    @Path("/{responderId}")
    @Transactional
    public Response update(@PathParam("responderId") Long responderId, Responder responder) {
        Optional<ResponderEntity> entity = repository.findByIdOptional(responderId);
        entity.ifPresent(r -> r.update(repository.toEntity(responder)));
        return Response.status(202).build();
    }

    @DELETE
    @Path("/{responderId}")
    @Transactional
    public Response delete(@PathParam("responderId") Long responderId) {
        repository.deleteById(responderId);
        return Response.status(204).build();
    }
}
