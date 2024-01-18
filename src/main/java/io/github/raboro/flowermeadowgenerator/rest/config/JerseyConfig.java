package io.github.raboro.flowermeadowgenerator.rest.config;

import io.github.raboro.flowermeadowgenerator.rest.resource.FlowerResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * @author Raboro
 */
@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(FlowerResource.class);
    }
}
