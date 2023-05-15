package com.gabriel.slot.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuration class for OpenAPI
 */
@Configuration
public class OpenApiConfig {

    @Value("${gnovoab.openapi.dev-url}")
    private transient String devUrl;

    @Value("${gnovoab.openapi.prod-url}")
    private transient String prodUrl;

    /**
     * Custom API definition
     */
    @Bean
    public OpenAPI customOpenApi() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("gnovoab@gmail.com");
        contact.setName("Gabriel");
        contact.setUrl("https://www.gnovoab.com");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Slot Machine API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage slot.").termsOfService("https://www.gnovoab.com/terms")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }


}