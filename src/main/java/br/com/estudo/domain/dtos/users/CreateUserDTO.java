package br.com.estudo.domain.dtos.users;

import br.com.estudo.domain.dtos.todos.CreateTodoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {


    private String email;
    private String password;
    private String role;

    private List<CreateTodoDTO> todoDTOS = new ArrayList<>();
}
