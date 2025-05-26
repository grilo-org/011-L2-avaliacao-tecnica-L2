package com.vinicius.empacotamento.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server().url("http://localhost:8080").description("Servidor Local"))
                .info(new Info()
                        .title("API de Empacotamento")
                        .version("1.0")
                        .description("Documentação completa da API de Empacotamento")
                        .contact(new Contact()
                                .name("Vinícius Nunes Bacelar")
                                .email("vinicius.bacelar11@outlook.com")));
    }

    @Bean
    public GroupedOpenApi allApi() {
        return GroupedOpenApi.builder()
                .group("todos-endpoints")
                .pathsToMatch("/api/**", "/v1/**")
                .packagesToScan("com.vinicius.empacotamento.controller")
                .build();
    }
}