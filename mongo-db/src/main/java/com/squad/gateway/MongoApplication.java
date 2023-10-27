package com.squad.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

@SpringBootApplication
@EnableWebFluxSecurity
public class MongoApplication {

  public static void main(String[] args) {
    SpringApplication.run(MongoApplication.class, args);
  }
}
