package com.squad.gateway.service;

import com.squad.gateway.dto.login.LoginDto;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface LoginService {

  Mono<ResponseEntity<String>> validateUser(LoginDto loginDto);
}
