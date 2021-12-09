package br.com.estudo;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "Bem-vindos",
                version = "1.0",
                description = "Api responsavel por organizar seu dia!",
                license = @License(name = "Apache 2.0", url = "https://foo.bar"),
                contact = @Contact(url = "https://gigantic-server.com", name = "Gabriel", email = "gabrielmaiasbu@gmail.com")
        )
)

public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
