package com.web.todo.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.web.todo.models.User;
import com.web.todo.models.User.CreateUser;
import com.web.todo.models.User.UpdateUser;
import com.web.todo.services.UserService;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

  @Autowired
  private UserService userService;

  // "localhost:8080/user/1"
  @GetMapping("/{id}")
  public ResponseEntity<User> findById(@PathVariable Long id) {
    User obj = this.userService.findById(id);
    return ResponseEntity.ok().body(obj);
  }

  @PostMapping
  @Validated(CreateUser.class)
  public ResponseEntity<Void> create(@Valid @RequestBody User obj) {
    this.userService.create(obj);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
          .buildAndExpand(obj.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @PutMapping("/{id}") // acionado quando uma requisição HTTP do tipo PUT for feita para o endpoint /user/{id}
  @Validated(UpdateUser.class) // Essa linha serve para validar os dados do usuário antes de atualizar.
  public ResponseEntity<Void> update(@Valid @RequestBody User obj, @PathVariable Long id) {
    obj.setId(id);
    this.userService.update(obj);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    this.userService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
