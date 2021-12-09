package br.com.estudo.models;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.persistence.*;

@Entity
@Introspected
@Data
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @Column
    private Boolean done;
}
