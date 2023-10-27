package org.squad.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.squad.persistence.entity.UserEntity;
import org.squad.persistence.repostiory.UserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final UserRepository userRepository;

  @GetMapping("/signup/{name}")
  public ResponseEntity<Mono<UserEntity>> signup(@PathVariable String name) {
    UserEntity userEntity = UserEntity.builder()
        .name(name)
        .build();

    return new ResponseEntity<>(userRepository.save(userEntity), HttpStatus.OK);
  }

  @GetMapping("/find/all")
  public ResponseEntity<Flux<UserEntity>> findAllUsers() {
    return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
  }
}
