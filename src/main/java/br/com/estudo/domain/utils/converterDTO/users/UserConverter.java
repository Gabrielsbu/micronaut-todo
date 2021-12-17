package br.com.estudo.domain.utils.converterDTO.users;

import br.com.estudo.domain.dtos.users.UserDTO;
import br.com.estudo.domain.models.User;
import br.com.estudo.domain.utils.converterDTO.todos.TodoConverter;
import org.mapstruct.Mapper;

@Mapper(uses = {TodoConverter.class})
public interface UserConverter {

    UserDTO toDTO(User user);

    User toModel(UserDTO userDTO);

}
