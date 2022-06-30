package io.github.pedroermarinho.comandalivreapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Locale;

@SpringBootApplication
@EnableJpaAuditing
@OpenAPIDefinition(
        info = @Info(
                title = "Comanda Livre API",
                version = "1.0",
                description = "Documentação da api",
                contact = @Contact(
                        name = "Pedro Marinho",
                        email = "pedroermarinho@gmail.com"
                ),
                license = @License(
                        url = "https://github.com/pedroermarinho/ComandaLivre-API/blob/main/LICENSE",
                        name = "MIT"
                )
        ),
        security = {
                @SecurityRequirement(
                        name = "accessToken"
                )
        }
)
public class ComandaLivreApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ComandaLivreApiApplication.class, args);
    }

}
