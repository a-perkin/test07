package com.alex.controller;

import com.alex.entity.Facing;
import com.alex.entity.Fireplace;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

import static com.alex.services.FireplaceService.getAllFireplaces;
import static com.alex.services.FireplaceService.getFireplace;
import static com.alex.services.FireplaceService.insertInFireplaceObj;

@Path("/fireplaces")
public class FireplaceController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFireplace() throws SQLException {

        return Response.status(200).entity(getAllFireplaces()).build();
    }

    @GET
    @Path("/one")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFireplaceOne(
            @QueryParam("id") int id) throws SQLException {

        return Response.status(200).entity(getFireplace(id)).build();
    }

    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertInFireplaceJSON(Fireplace fireplace) throws SQLException {
        Object fireplace1 = insertInFireplaceObj(fireplace);

        return Response.status(200).entity(fireplace1).build();
    }




}