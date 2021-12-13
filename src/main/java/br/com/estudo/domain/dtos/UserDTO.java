package br.com.estudo.domain.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {

    private Long id;
    private String email;
}
