package com.squad.gateway.persistence.repository;

import com.squad.gateway.persistence.entity.UserDetailsEntity;
import java.util.UUID;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserDetailsRepository extends ReactiveMongoRepository<UserDetailsEntity, UUID> {

  Mono<UserDetailsEntity> findByUsername(String username);

  Flux<UserDetailsEntity> findAllByEnabled(boolean isEnabled);
}
