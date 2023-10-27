package com.squad.gateway.service;

import com.squad.gateway.config.security.ReactiveUser.UserDetailsDto;
import com.squad.gateway.dto.register.UserSignupDto;
import com.squad.gateway.dto.response.UserResponse;
import com.squad.gateway.persistence.entity.UserDetailsEntity;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

  Mono<ResponseEntity<String>> signup(UserSignupDto userSignupDto);

  ResponseEntity<Flux<UserResponse>> findAllUsers();

  ResponseEntity<Flux<UserResponse>> findAllActiveUsers();

  ResponseEntity<Flux<UserResponse>> findAllInactiveUsers();

  Mono<ResponseEntity<UserResponse>> findSingleUser(String userId);
}
