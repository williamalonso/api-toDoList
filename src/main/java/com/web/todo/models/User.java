package com.web.todo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // incrementa automaticamente o 'id'
    @Column(name = "id", unique = true) // Nome da minha coluna. Isso é opcional
    private Long id;

    @Column(name = "username", length = 100, nullable = false, unique = true) // Estamos dizendo que essa coluna vai ter um varchar de tamanho 100 ao invés de 255 padrão. Também estamos dizendo que esse campo/coluna não pode ser nulo.
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 100)
    private String username;

    @Column(name = "password", length = 60, nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 8, max = 60)
    private String password;
    
}
