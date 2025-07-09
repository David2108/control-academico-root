package com.web.app.controlacademico.app.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Control Académico API")
                                .version("v1.0.0")
                                .description("API REST para gestión académica")
                                .contact(
                                        new Contact()
                                                .name("Equipo Backend")
                                                .email("davidv2108@gmail.com")
                                )
                                .license(
                                        new License()
                                                .name("MIT")
                                )
                )
                .externalDocs(
                        new ExternalDocumentation()
                                .description("Documentación completa")
                                .url("https://github.com/David2108/control-academico-root")
                );
    }

}
