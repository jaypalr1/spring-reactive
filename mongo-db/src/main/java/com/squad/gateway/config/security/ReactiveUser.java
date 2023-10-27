package com.squad.gateway.config.security;

import com.squad.gateway.persistence.entity.UserDetailsEntity;
import com.squad.gateway.persistence.repository.UserDetailsRepository;
import java.util.Collections;
import java.util.stream.Collectors;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsPasswordService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReactiveUser implements ReactiveUserDetailsService,
    ReactiveUserDetailsPasswordService {

  private final UserDetailsRepository userDetailsRepository;

  @Override
  public Mono<UserDetails> findByUsername(String username) {
    return userDetailsRepository.findByUsername(username)
//        .singleOrEmpty() // Use when returning Flux object
        .map(UserDetailsDto::new);
  }

  @Override
  public Mono<UserDetails> updatePassword(UserDetails userDetails, String newPassword) {
    return this.userDetailsRepository.findByUsername(userDetails.getUsername())
        .doOnSuccess(user -> user.setPassword(newPassword))
        .flatMap(userDetailsRepository::save)
        .map(UserDetailsDto::new);
  }

  @EqualsAndHashCode(callSuper = true)
  public static class UserDetailsDto extends User implements UserDetails {

    public UserDetailsDto() {
      super(null, null, Collections.emptyList());
    }

    public UserDetailsDto(UserDetailsEntity user) {
      super(user.getUsername(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(),
          user.isCredentialsNonExpired(), user.isAccountNonLocked(),
          user.getAuthorities()
              .stream()
              .map(SimpleGrantedAuthority::new)
              .collect(Collectors.toList()));
    }
  }
}
