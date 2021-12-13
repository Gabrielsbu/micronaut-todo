package br.com.estudo.domain.utils.converterDTO.users;

import br.com.estudo.domain.dtos.UserDTO;
import br.com.estudo.domain.models.User;
import jakarta.inject.Singleton;
import org.modelmapper.ModelMapper;

@Singleton
public class UserMapper {

    ModelMapper modelMapper = new ModelMapper();

    public UserDTO toDto(User user){
        return modelMapper.map(user, UserDTO.class);
    }
}
