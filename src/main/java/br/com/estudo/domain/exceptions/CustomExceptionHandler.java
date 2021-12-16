package br.com.estudo.domain.exceptions;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Produces
@Singleton
@Requires(classes = { GlobalException.class, CustomExceptionHandler.class })
public class CustomExceptionHandler
        implements ExceptionHandler<GlobalException, HttpResponse<ErrorMessage>> {

    @Override
    public HttpResponse<ErrorMessage> handle(HttpRequest request, GlobalException exception) {
        ErrorMessage message = new ErrorMessage();
        message.setMessage(exception.getMessage());
        message.setStatus(exception.getStatus().getCode());
        return HttpResponse.status(exception.getStatus())
                .body(message);//TODO: essa é a forma correta de utilizar o HttpResponse, com o .serverError() você está jogando o status 500 e depois mudando.
    }
}