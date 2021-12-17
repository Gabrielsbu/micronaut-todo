package br.com.estudo.domain.dtos.todos;

import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Introspected
public class TodoDTO {

    private Long todoId;
    private String description;
    private Boolean done;
    private LocalDateTime dateValidate;
}
