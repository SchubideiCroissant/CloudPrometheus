package de.home.vs.rest.prometheus;


import io.prometheus.client.Counter;

public class MetricsCollector {

    public static final Counter artikelRequests = Counter.build()
            .name("artikel_requests_total")
            .help("Gesamtanzahl der GET-Anfragen auf /artikel")
            .register(); // Registrierung im Registry

    public static void incArtikelRequests() {
        artikelRequests.inc();
    }

    // Weitere Metriken folgen hier
}
