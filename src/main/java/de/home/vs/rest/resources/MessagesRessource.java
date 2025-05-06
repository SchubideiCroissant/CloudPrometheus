package de.home.vs.rest.resources;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.json.Json.createArrayBuilder;
import static javax.json.Json.createObjectBuilder;

@Path("messages")
public class MessagesRessource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getRessourceAsText() {
        return Response
                .status(Response.Status.OK)
                .entity("This is a 'Hello World' message \n")
                .build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRessourceAsJson() {
        JsonObjectBuilder job = createObjectBuilder();
        JsonArrayBuilder jab = createArrayBuilder();
        job.add("message", "Hello World");
        jab.add(44);
        jab.add(true);
        job.add("myArray1", jab);
        return Response
                .status(Response.Status.OK)
                .entity(job.build())
                .build();
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response RessourceByIdAsJson(@PathParam("id") int id) {
        JsonObjectBuilder job = createObjectBuilder();
        job.add("message", "This is message with ID: " + id);
        return Response
                .status(Response.Status.OK)
                .entity(job.build())
                .build();
    }

}