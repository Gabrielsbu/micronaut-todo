package br.com.estudo.domain.dtos;

import lombok.Data;

@Data
public class UpdateUserDTO {

    private String email;
    private String password;
}
