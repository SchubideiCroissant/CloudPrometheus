package de.home.vs.rest.prometheus;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.StringWriter;


@Path("/metrics")
public class MetricsRessource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMetrics() {
        StringWriter writer = new StringWriter();
        try {
            TextFormat.write004(writer, CollectorRegistry.defaultRegistry.metricFamilySamples());
        } catch (IOException e) {
            return "# Fehler beim Schreiben der Metriken: " + e.getMessage();
        }
        return writer.toString();
    }
}

