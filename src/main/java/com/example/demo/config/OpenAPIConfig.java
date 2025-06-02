package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

    @Value("${dominio.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Trabalho Final de API RESTful - Serratec");
        Contact contact = new Contact();
        contact.setEmail("bernardo@rbennes@gmail.com");
        contact.setName("Grupo 6");
        contact.setUrl("https://serratec.org/");
        License apacheLicense = new License().name("Apache License").url("https://www.apache.org/licenses/LICENSE-2.0");

        Info info = new Info().title("APIfinal").version("1.0").contact(contact)
                .description("API exemplo de um sistema de E-Commerce").termsOfService("https://www.meudominio.com.br/termos")
                .license(apacheLicense);
        return new OpenAPI().info(info).addServersItem(devServer);
    }
}