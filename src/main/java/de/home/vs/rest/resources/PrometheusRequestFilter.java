package de.home.vs.rest.resources;

import de.home.vs.rest.prometheus.MetricsCollector;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class PrometheusRequestFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if ("GET".equals(requestContext.getMethod())) {
            String path = requestContext.getUriInfo().getPath();
            MetricsCollector.incRequestForPath(path);
        }
    }
}


