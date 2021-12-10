package br.com.estudo.core;

import br.com.estudo.models.User;
import br.com.estudo.repositories.UserRepository;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.util.annotation.Nullable;

@Singleton
@RequiredArgsConstructor
public class AuthProvider implements AuthenticationProvider {

    private final UserRepository userRepository;

    @Override
    public Publisher<AuthenticationResponse> authenticate(@Nullable HttpRequest<?> httpRequest,
                                                          AuthenticationRequest<?, ?> authenticationRequest) {

        User user = userRepository.findByEmail(authenticationRequest.getIdentity().toString());

        return Flux.create(emitter -> {
            if (authenticationRequest.getIdentity().equals(user.getEmail()) &&
                    authenticationRequest.getSecret().equals(user.getPassword())) {
                emitter.next(AuthenticationResponse.success((String) authenticationRequest.getIdentity()));
                emitter.complete();
            } else {
                emitter.error(AuthenticationResponse.exception());
            }
        }, FluxSink.OverflowStrategy.ERROR);
    }

}
