package br.com.estudo.domain.services.Impl;

import br.com.estudo.domain.dtos.UpdateUserDTO;
import br.com.estudo.domain.dtos.UserDTO;
import br.com.estudo.domain.models.User;
import br.com.estudo.domain.repositories.UserRepository;
import br.com.estudo.domain.repositories.params.UserParams;
import br.com.estudo.domain.services.UserService;
import br.com.estudo.domain.utils.converterDTO.users.UserMapper;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Singleton
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Page<User> getAllUsers(UserParams params, Pageable pageable) {
        return userRepository.searchAllUsers(params, pageable);
    }

    @Override
    public User createUser(User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();//TODO: sempre recomendo isolar esse processo, por que possívelmente vai ser usado em outros lugares
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id);
        return userMapper.toDto(user);
    }

    /*
     * TODO: a camada de service não deve retornar algo no formato do controller(HttResponse),
     *  Por que em tese a camada service deve servir para qualquer gateway(fila, Rest, Graphql...)
     */
    @Override
    public HttpResponse<Void> deleteUserById(Long id) {
        User user = userRepository.findById(id);

        userRepository.deleteById(user.getId());

        return HttpResponse.noContent();
    }

    @Override
    public User updateUser(Long id, UpdateUserDTO updateUserDTO) {
        User userExistent = userRepository.findById(id);
        userExistent.setEmail(updateUserDTO.getEmail());
        userExistent.setPassword(updateUserDTO.getPassword());

        return userRepository.update(userExistent);
    }
}
