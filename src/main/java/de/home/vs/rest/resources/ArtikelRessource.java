package de.home.vs.rest.resources;

import de.home.vs.rest.model.Artikel;
import de.home.vs.rest.model.ArtikelVerwaltung;
import de.home.vs.rest.prometheus.MetricsCollector;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/artikel")
public class ArtikelRessource {
    ArtikelVerwaltung av = ArtikelVerwaltung.getInstance();
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetArtikelById(@PathParam("id") int id) {
        JsonObjectBuilder job = javax.json.Json.createObjectBuilder();
        Artikel a = av.getArtikelById(id);
        return getResponse(job, a);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArtikel() {
        JsonObjectBuilder job = javax.json.Json.createObjectBuilder();
        JsonArrayBuilder jab = javax.json.Json.createArrayBuilder(); // Array
        List<Artikel> artikelList = av.getAlleArtikel();

        for(Artikel a: artikelList) {
            JsonObjectBuilder sjob = javax.json.Json.createObjectBuilder();
            sjob.add("link", "http://localhost:8080/rest/artikel/"+ a.getArtikelnummer() );
            sjob.add("artikelnummer", a.getArtikelnummer());
            sjob.add("name", a.getName());
            //sjob.add("details", a.getDetails());
            sjob.add("preis", a.getPreis());
            sjob.add("lagerbestand", a.getLagerbestand());
            jab.add(sjob);
        }
        job.add("artikel", jab);
        return Response
                .status(Response.Status.OK)
                .entity(job.build())
                .build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response changePrice(@PathParam("id") int id, JsonObject input) {
        ArtikelVerwaltung av = ArtikelVerwaltung.getInstance();
        Artikel a = av.getArtikelById(id);

        if (a == null) {
            JsonObject error = javax.json.Json.createObjectBuilder()
                    .add("error", "Artikel mit ID " + id + " nicht gefunden.")
                    .build();
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }

        // Preis ändern
        if (input.containsKey("preis")) {
            double neuerPreis = input.getJsonNumber("preis").doubleValue();
            av.aktualisiereArtikelPreis(id,neuerPreis);
        }

        JsonObjectBuilder job = javax.json.Json.createObjectBuilder();
        return getResponse(job, a);
    }

    private Response getResponse(JsonObjectBuilder job, Artikel a) {
        job.add("artikelnummer", a.getArtikelnummer());
        job.add("name", a.getName());
        job.add("details", a.getDetails());
        job.add("preis", a.getPreis());
        job.add("lagerbestand", a.getLagerbestand());
        return Response
                .status(Response.Status.OK)
                .entity(job.build())
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addArtikel(JsonObject input) {
        if (!input.containsKey("name") || !input.containsKey("details")
                || !input.containsKey("preis") || !input.containsKey("lagerbestand")) {
            JsonObject error = javax.json.Json.createObjectBuilder()
                    .add("error", "Fehlende Felder im JSON.")
                    .build();
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        }

        String name = input.getString("name");
        String details = input.getString("details");
        double preis = input.getJsonNumber("preis").doubleValue();
        int lagerbestand = input.getInt("lagerbestand");

        ArtikelVerwaltung av = ArtikelVerwaltung.getInstance();
        Artikel a = av.addArtikel(name, details, preis, lagerbestand);

        JsonObjectBuilder job = javax.json.Json.createObjectBuilder();
        job.add("link", "http://localhost:8080/rest/artikel/"+a.getArtikelnummer());
        job.add("message", "Artikel wurde hinzugefügt.");
        return Response.status(Response.Status.CREATED).entity(job.build()).build();
    }



}
