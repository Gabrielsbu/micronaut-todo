package br.com.estudo.domain.services.Impl;

import br.com.estudo.domain.dtos.UpdateUserDTO;
import br.com.estudo.domain.models.User;
import br.com.estudo.domain.repositories.UserRepository;
import br.com.estudo.domain.repositories.params.UserParams;
import br.com.estudo.domain.services.UserService;
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

    @Override
    public Page<User> getAllUsers(UserParams params, Pageable pageable) {
        return userRepository.searchAllUsers(params, pageable);
    }

    @Override
    public User createUser(User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public HttpResponse<Void> deleteUserById(Long id) {
        User user = getUserById(id);

        userRepository.deleteById(user.getId());

        return HttpResponse.noContent();
    }

    @Override
    public User updateUser(Long id, UpdateUserDTO updateUserDTO) {
        User userExistent = getUserById(id);
        userExistent.setEmail(updateUserDTO.getEmail());
        userExistent.setPassword(updateUserDTO.getPassword());

        return userRepository.update(userExistent);
    }
}
