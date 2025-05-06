package de.home.vs.rest.resources;

import de.home.vs.rest.model.ArtikelVerwaltung;
import de.home.vs.rest.model.DataSource;
import de.home.vs.rest.model.Student;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("students")
public class StudentsRessource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudents() {
        JsonObjectBuilder job = javax.json.Json.createObjectBuilder();
        JsonArrayBuilder jab = javax.json.Json.createArrayBuilder();
        DataSource ds = DataSource.getInstance();
        ArrayList<Student> studentList = ds.getAllStudents();

        for(Student s: studentList) {
            JsonObjectBuilder sjob = javax.json.Json.createObjectBuilder();
            sjob.add("studentId", s.getMatNr());
            sjob.add("firstName", s.getFirstName());
            sjob.add("familyName", s.getFamilyName());
            jab.add(sjob);
        }
        job.add("students", jab);
        return Response
                .status(Response.Status.OK)
                .entity(job.build())
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addStudent(JsonObject jsonObject) {
        DataSource ds = DataSource.getInstance();

        Student s = new Student(ds.getNewStudentId(), jsonObject.getString("firstName"), jsonObject.getString("familyName"));
        ds.addStudent(s);

        JsonObjectBuilder job = javax.json.Json.createObjectBuilder();
        job.add("matNr", s.getMatNr());
        return Response
                .status(Response.Status.OK)
                .entity(job.build())
                .build();
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetStudentsById(@PathParam("id") int id) {
        JsonObjectBuilder job = javax.json.Json.createObjectBuilder();
        job.add("message", "This is message with ID: " + id);
        return Response
                .status(Response.Status.OK)
                .entity(job.build())
                .build();
    }
}

