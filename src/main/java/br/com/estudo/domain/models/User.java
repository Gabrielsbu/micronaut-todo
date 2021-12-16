package br.com.estudo.domain.models;

import io.micronaut.core.annotation.Introspected;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity(name = "users")
@Introspected
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank//TODO: É possível adicionar o Javax Validator para obrigar o envio de alguns dados, com o e-mail
    private String email;

    @Column
    @Size(min = 6)
    private String password;

    @Builder.Default
    private String role = "VIEW";//TODO: Talvez criar um ENUM com as roles

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist//TODO: O micronaut já conta com uma anotação para isso: @DateCreated
    public void prePersist(){
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate//TODO: O micronaut já conta com uma anotação para isso: @DateUpdated
    public void preUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
}
