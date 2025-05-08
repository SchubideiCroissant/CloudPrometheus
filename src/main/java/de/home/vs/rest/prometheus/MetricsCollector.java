package de.home.vs.rest.prometheus;


import io.prometheus.client.Counter;

public class MetricsCollector {
// Hier werden alle Prometheus Counter usw erstellt

    private static final Counter getRequestsTotal = Counter.build()
            .name("http_get_requests_total")
            .help("Anzahl der HTTP-GET-Requests")
            .labelNames("path") // Filter orndet später automatisch den Path hinzu
            .register();


    public static void incRequestForPath(String path) {
        getRequestsTotal.labels(path).inc();
    }



    // Weitere Metriken folgen hier
}
