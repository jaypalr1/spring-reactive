package com.squad.gateway.service.impl;

import com.squad.gateway.dto.login.LoginDto;
import com.squad.gateway.service.LoginService;
import com.squad.gateway.utility.DefaultUserUtility;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

  private final PasswordEncoder passwordEncoder;
  private final ReactiveUserDetailsService authenticationManager;

  @Override
  public Mono<ResponseEntity<String>> validateUser(LoginDto loginDto) {
    return authenticationManager
        .findByUsername(loginDto.getUsername())
        .defaultIfEmpty(DefaultUserUtility.getDefaultUser())
        .map(user -> {
          if (Objects.nonNull(user.getUsername())) {
            if (!user.isEnabled()) {
              return new ResponseEntity<>("Account inactive.", HttpStatus.UPGRADE_REQUIRED);
            }

            if (!user.isCredentialsNonExpired()) {
              return new ResponseEntity<>("Credentials expired.", HttpStatus.UPGRADE_REQUIRED);
            }

            if (!user.isAccountNonLocked()) {
              return new ResponseEntity<>("Account locked.", HttpStatus.LOCKED);
            }

            if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
              return
                  new ResponseEntity<>(
//                  jwtService.generateToken(user.getUsername())
                      "Success"
                      , HttpStatus.OK);
            }

            return new ResponseEntity<>("Invalid credentials.", HttpStatus.UNAUTHORIZED);
          }

          return new ResponseEntity<>("User not found.", HttpStatus.BAD_REQUEST);
        });
  }
}
