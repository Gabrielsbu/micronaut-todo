package br.com.estudo.domain.dtos.todos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set")
public class CreateTodoDTO {
    private Long userId;
    private String description;
    private Integer days;
}
