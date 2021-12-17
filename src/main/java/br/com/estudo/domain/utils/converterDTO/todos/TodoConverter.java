package br.com.estudo.domain.utils.converterDTO.todos;

import br.com.estudo.domain.dtos.todos.TodoDTO;
import br.com.estudo.domain.models.Todo;
import org.mapstruct.Mapper;

@Mapper
public interface TodoConverter {

    TodoDTO toDTO(Todo todo);

    Todo toModel(TodoDTO todoDTO);
}
