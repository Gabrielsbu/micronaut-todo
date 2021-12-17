package br.com.estudo.domain.services.Impl;

import br.com.estudo.domain.dtos.users.CreateUserDTO;
import br.com.estudo.domain.dtos.users.UpdateUserDTO;
import br.com.estudo.domain.dtos.users.UserDTO;
import br.com.estudo.domain.models.User;
import br.com.estudo.domain.repositories.UserRepository;
import br.com.estudo.domain.repositories.params.UserParams;
import br.com.estudo.domain.services.UserService;
import br.com.estudo.domain.utils.converterDTO.users.UserConverter;
import br.com.estudo.domain.utils.converterDTO.users.UserConverterImpl;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.transaction.annotation.ReadOnly;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Singleton
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter = new UserConverterImpl();

    private String encodePassword(String password){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

    @Override
    public Page<UserDTO> getAllUsers(UserParams params, Pageable pageable) {
        Page<User> users = userRepository.searchAllUsers(params, pageable);
        return users.map(userConverter::toDTO);
    }

    @Override
    public UserDTO getUserById(Long userId) {
        return userConverter.toDTO(userRepository.findById(userId));
    }

    @Override
    public User createUser(CreateUserDTO createUserDTO) {
        String encodedPassword = encodePassword(createUserDTO.getPassword());

        User userCreated = new User();
        userCreated.setEmail(createUserDTO.getEmail());
        userCreated.setPassword(encodedPassword);
        return userRepository.save(userCreated);
    }

    @Override
    public HttpResponse<Void> deleteUserById(Long userId) {
        User user = userRepository.findById(userId);
        userRepository.deleteById(user.getUserId());

        return HttpResponse.noContent();
    }

    @Override
    public User updateUser(Long userId, UpdateUserDTO updateUserDTO) {
        User userExistent = userRepository.findById(userId);
        String encodedPassword = updateUserDTO.getPassword();

        if(updateUserDTO.getPassword() != null) {
            encodedPassword = encodePassword(updateUserDTO.getPassword());
        }

        userExistent.setEmail(updateUserDTO.getEmail());
        userExistent.setPassword(encodedPassword);

        return userRepository.update(userExistent);
    }
}
