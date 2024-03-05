package com.autoestudo.gerenciadorDeMembros;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;

public class SwaggerConfig {

    @Bean
    public OpenAPI openApi() {
        Info info = new Info()
                .description("API documentation.")
                .title("Gerenciador de Membros");

        return new OpenAPI()
                .info(info)
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        "Bearer",
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                                .in(SecurityScheme.In.HEADER)
                                                .name("Authorization")
                                )
                );
    }

}