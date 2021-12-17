package br.com.estudo.domain.dtos.users;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long userId;
    private String email;
    private String password;
}
