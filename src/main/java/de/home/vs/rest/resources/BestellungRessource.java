package de.home.vs.rest.resources;

import de.home.vs.rest.model.*;

import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("bestellungen")
public class BestellungRessource {


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBestellung(JsonObject input) {
        ArtikelVerwaltung av = ArtikelVerwaltung.getInstance();
        CustomerVerwaltung cv = CustomerVerwaltung.getInstance();
        Customer c = cv.getCustomerById(input.getInt("customerId"));

        if (!input.containsKey("artikel")) {
            JsonObject error = Json.createObjectBuilder()
                    .add("error", "Bestellung benötigt eine 'artikel'-Liste.")
                    .build();
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        }
        if (c == null) {
            JsonObject error = Json.createObjectBuilder()
                    .add("error", "Kunde mit ID " + input.getInt("customerId") + " nicht gefunden.")
                    .build();
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        }

        JsonArray postenArray = input.getJsonArray("artikel");
        List<Bestellposten> posten = new ArrayList<>();

        for (JsonValue v : postenArray) {
            JsonObject obj = v.asJsonObject();

            int artikelnummer = obj.getInt("artikelnummer");
            int menge = obj.getInt("menge");

            Artikel a = av.getArtikelById(artikelnummer);
            if (a == null) continue;

            posten.add(new Bestellposten(a, menge));
        }

        Bestellung b = BestellungsVerwaltung.getInstance().addBestellung(posten, c);

        JsonObjectBuilder response = Json.createObjectBuilder()
                .add("customerId", c.getId())
                .add("vorname", b.getCustomer().getFirstName())
                .add("nachname", b.getCustomer().getFamilyName())
                .add("bestellnummer", b.getBestellnummer())
                .add("zeit", b.getZeit().toString());

        return Response.status(Response.Status.CREATED).entity(response.build()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlleBestellungen() {
        List<Bestellung> alle = BestellungsVerwaltung.getInstance().getAlleBestellungen();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for (Bestellung b : alle) {
            arrayBuilder.add(Json.createObjectBuilder()
                    .add("customerId",b.getCustomer().getId() )
                    .add("bestellnummer", b.getBestellnummer())
                    .add("zeit", b.getZeit().toString())
            );
        }

        JsonObject result = Json.createObjectBuilder()
                .add("bestellungen", arrayBuilder)
                .build();

        return Response.status(Response.Status.OK).entity(result).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBestellungById(@PathParam("id") int id) {
        Bestellung bestellung = BestellungsVerwaltung.getInstance().getBestellungById(id);

        if (bestellung == null) {
            JsonObject error = Json.createObjectBuilder()
                    .add("error", "Bestellung mit ID " + id + " nicht gefunden.")
                    .build();
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }

        JsonArrayBuilder postenArray = Json.createArrayBuilder();
        for (Bestellposten bp : bestellung.getPosten()) {
            Artikel a = bp.getArtikel();

            JsonObject artikelJson = Json.createObjectBuilder()
                    .add("artikelnummer", a.getArtikelnummer())
                    .add("name", a.getName())
                    .add("details", a.getDetails())
                    .add("preis", a.getPreis())
                    .add("menge", bp.getMenge()) // Hier Menge aus Bestellung nicht aus Bestand
                    .build();

            postenArray.add(artikelJson);
        }

        JsonObject result = Json.createObjectBuilder()
                .add("customerId", bestellung.getCustomer().getId())
                .add("bestellnummer", bestellung.getBestellnummer())
                .add("zeit", bestellung.getZeit().toString())
                .add("artikel", postenArray)
                .build();

        return Response.status(Response.Status.OK).entity(result).build();
    }




}
