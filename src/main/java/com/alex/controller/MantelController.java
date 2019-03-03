package com.alex.controller;

import com.alex.entity.Mantel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

import static com.alex.services.MantelService.getMantelById;
import static com.alex.services.MantelService.getMantels;
import static com.alex.services.MantelService.insertInMantelObj;

@Path("/mantels")
public class MantelController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMantels() throws SQLException {

        return Response.status(200).entity(getMantels()).build();

    }

    @GET
    @Path("/one")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMantelOne(
            @QueryParam("id") int id) throws SQLException {

        return Response.status(200).entity(getMantelById(id)).build();
    }


    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertInMantelsJSON(Mantel mantel) throws SQLException {
        Object mantel1 = insertInMantelObj(mantel);

        return Response.status(200).entity(mantel1).build();
    }
}