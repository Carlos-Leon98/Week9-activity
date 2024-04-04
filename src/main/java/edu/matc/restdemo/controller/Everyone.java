package edu.matc.restdemo.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/everyone")
public class Everyone {

    @GET
    @Path("{number}")
    public Response getUserById(@PathParam("number") String number) {

        return Response
                .status(200)
                .entity("Everyone picked a random number : " + number)
                .build();

    }

    @GET
    public Response helloFromEveryone() {
        return Response
                .status(200)
                .entity("Hello from everyone")
                .build();
    }

}