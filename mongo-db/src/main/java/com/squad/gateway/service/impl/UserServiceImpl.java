package com.squad.gateway.service.impl;

import com.squad.gateway.dto.register.UserSignupDto;
import com.squad.gateway.dto.response.UserResponse;
import com.squad.gateway.exception.custom.GatewayException;
import com.squad.gateway.persistence.entity.UserDetailsEntity;
import com.squad.gateway.persistence.repository.UserDetailsRepository;
import com.squad.gateway.service.UserService;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final PasswordEncoder passwordEncoder;
  private final UserDetailsRepository userDetailsRepository;

  @Override
  public Mono<ResponseEntity<String>> signup(UserSignupDto userSignupDto) {
    return userDetailsRepository
        .findByUsername(userSignupDto.getUsername())
        .defaultIfEmpty(new UserDetailsEntity())
        .flatMap(user -> {
          if (Objects.isNull(user.getUsername())) {
            UserDetailsEntity userDetailsEntity = buildUserEntity(userSignupDto);

            return userDetailsRepository
                .insert(userDetailsEntity)
                .map(u -> new ResponseEntity<>("Registered successfully", HttpStatus.ACCEPTED))
                .defaultIfEmpty(
                    new ResponseEntity<>("Registration failed", HttpStatus.NOT_ACCEPTABLE));
          } else {
            return Mono.error(new GatewayException("User already exist.", HttpStatus.BAD_REQUEST));
          }
        });
  }

  @Override
  public ResponseEntity<Flux<UserResponse>> findAllUsers() {
    return new ResponseEntity<>(userDetailsRepository.findAll().map(this::buildUserResponse),
        HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Flux<UserResponse>> findAllActiveUsers() {
    return new ResponseEntity<>(userDetailsRepository
        .findAllByEnabled(true)
        .map(this::buildUserResponse), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Flux<UserResponse>> findAllInactiveUsers() {
    return new ResponseEntity<>(userDetailsRepository
        .findAllByEnabled(false)
        .map(this::buildUserResponse), HttpStatus.OK);
  }

  @Override
  public Mono<ResponseEntity<UserResponse>> findSingleUser(String userId) {
    return userDetailsRepository.findByUsername(userId)
        .map(user -> new ResponseEntity<>(buildUserResponse(user), HttpStatus.OK))
        .defaultIfEmpty(new ResponseEntity<>(new UserResponse(), HttpStatus.NOT_FOUND));
  }

  private UserDetailsEntity buildUserEntity(UserSignupDto userSignupDto) {
    return UserDetailsEntity.builder()
        .id(UUID.randomUUID())
        .name(userSignupDto.getFirstName())
        .password(passwordEncoder.encode(userSignupDto.getPassword()))
        .username(userSignupDto.getUsername())
        .authorities(Collections.singletonList("USER"))
        .credentialsNonExpired(true)
        .accountNonLocked(true)
        .accountNonExpired(true)
        .enabled(false)
        .build();
  }

  private UserResponse buildUserResponse(UserDetailsEntity userDetailsEntity) {
    return UserResponse.builder()
        .name(userDetailsEntity.getName())
        .username(userDetailsEntity.getUsername())
        .credentialsNonExpired(userDetailsEntity.isCredentialsNonExpired())
        .accountNonLocked(userDetailsEntity.isAccountNonLocked())
        .accountNonExpired(userDetailsEntity.isAccountNonExpired())
        .enabled(userDetailsEntity.isEnabled())
        .build();
  }
}
