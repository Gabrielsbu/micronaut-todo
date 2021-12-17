package br.com.estudo.domain.dtos.users;

import lombok.Data;

@Data
public class UpdateUserDTO {

    private String email;
    private String password;
}
