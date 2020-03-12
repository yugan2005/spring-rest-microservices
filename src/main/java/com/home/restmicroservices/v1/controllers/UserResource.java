package com.home.restmicroservices.v1.controllers;

import com.home.restmicroservices.dto.UserDto;
import com.home.restmicroservices.exceptions.NoUserFoundException;
import com.home.restmicroservices.v1.services.UserService;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/api/v1/users")
public class UserResource {
  private final UserService userService;

  public UserResource(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(path = "")
  public List<UserDto> allUsers() {
    return userService.getAll();
  }

  @GetMapping(path = "/{id}")
  public EntityModel<UserDto> user(@PathVariable int id) {
    EntityModel<UserDto> entityModel = new EntityModel<>(userService.getById(id));
    final Link allUsers =
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).allUsers()).withRel("allUsers");
    return entityModel.add(allUsers);
  }

  @PostMapping(path = "")
  public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
    UserDto savedUserDto = userService.save(userDto);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
        buildAndExpand(savedUserDto.getId()).toUri();
    return ResponseEntity.created(location).build();
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable int id) {
    if (userService.deleteById(id)) {
      return ResponseEntity.status(HttpStatus.OK).build();
    }

    throw new NoUserFoundException("delete user ID: " + id + " not found");
  }
}
