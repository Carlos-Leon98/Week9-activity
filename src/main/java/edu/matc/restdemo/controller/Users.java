package edu.matc.restdemo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.matc.restdemo.entity.User;
import edu.matc.restdemo.persistence.UserDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class Users {
    UserDAO userDAO;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") int id) throws JsonProcessingException {
        userDAO = new UserDAO();
        User user = userDAO.getUserById(id);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String json = objectMapper.writeValueAsString(user);

        if (user != null) {
            return Response
                    .status(Response.Status.OK)
                    .entity(json)
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("User with id " + id + " not found")
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() throws JsonProcessingException {
        userDAO = new UserDAO();

        List<User> users = userDAO.getAll();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String json = objectMapper.writeValueAsString(users);

        return Response
                .status(Response.Status.OK)
                .entity(json)
                .build();
    }
}