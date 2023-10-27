package com.squad.gateway.controller;

import com.squad.gateway.dto.login.LoginDto;
import com.squad.gateway.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {

  private final LoginService loginService;

  @PostMapping("/login")
  public Mono<ResponseEntity<String>> login(@RequestBody @Valid LoginDto loginDto) {
    return loginService.validateUser(loginDto);
  }
}
