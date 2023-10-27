package com.squad.gateway.controller;

import com.squad.gateway.config.security.ReactiveUser.UserDetailsDto;
import com.squad.gateway.dto.register.UserSignupDto;
import com.squad.gateway.dto.response.UserResponse;
import com.squad.gateway.persistence.entity.UserDetailsEntity;
import com.squad.gateway.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  @PostMapping("/signup")
  public Mono<ResponseEntity<String>> signup(@RequestBody @Valid UserSignupDto userSignupDto) {
    return userService.signup(userSignupDto);
  }

  @GetMapping("/find/all")
  public ResponseEntity<Flux<UserResponse>> findAllUsers() {
    return userService.findAllUsers();
  }

  @GetMapping("/find/all/active")
  public ResponseEntity<Flux<UserResponse>> findAllActiveUsers() {
    return userService.findAllActiveUsers();
  }

  @GetMapping("/find/all/inactive")
  public ResponseEntity<Flux<UserResponse>> findAllInactiveUsers() {
    return userService.findAllInactiveUsers();
  }

  @PatchMapping("/find/{userId}")
  public Mono<ResponseEntity<UserResponse>> findSingleUser(String userId) {
    return userService.findSingleUser(userId);
  }
}
