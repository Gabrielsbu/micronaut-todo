package br.com.estudo.domain.dtos.todos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set")
public class CreateTodoDTO {
    private Long todoId;
    private String description;
    private Integer days;
}
