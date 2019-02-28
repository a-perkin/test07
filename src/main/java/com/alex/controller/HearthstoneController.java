package com.alex.controller;


import com.alex.entity.Hearthstone;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

import static com.alex.services.HearthstoneService.getHearthstones;
import static com.alex.services.HearthstoneService.insertInHearthstoneObj;


@Path("/hearthstones")
public class HearthstoneController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllHearthstones() throws SQLException {

        return Response.status(200).entity(getHearthstones()).build();

    }


    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertInHearthstoneJSON(Hearthstone hearthstone) throws SQLException {
        Object hearthstone1 = insertInHearthstoneObj(hearthstone);

        return Response.status(200).entity(hearthstone1).build();
    }
}