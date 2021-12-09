package br.com.estudo.repositories.params;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.annotation.QueryValue;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Introspected
public class TodoParams {

    @QueryValue
    private String description;
}
