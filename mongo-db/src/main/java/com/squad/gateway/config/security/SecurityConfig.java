package com.squad.gateway.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity.CorsSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.CsrfSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.FormLoginSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.HttpBasicSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.LogoutSpec;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

//  private final ReactiveUserDetailsService reactiveUser;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

/*
  @Bean
  public MapReactiveUserDetailsService userDetailsService() {
    return new MapReactiveUserDetailsService(User.withDefaultPasswordEncoder()
        .username("user")
        .password("user")
        .roles("USER")
        .build());
  }

  @Bean
  ObservationRegistryCustomizer<ObservationRegistry> addTextHandler() {
    return registry -> registry
        .observationConfig()
        .observationHandler(new ObservationTextHandler());
  }

  @Bean
  public ReactiveAuthenticationManager authenticationManager() {
    UserDetailsRepositoryReactiveAuthenticationManager authenticationManager =
        new UserDetailsRepositoryReactiveAuthenticationManager(reactiveUser);
    authenticationManager.setPasswordEncoder(passwordEncoder());

    return authenticationManager;
  }*/

  @Bean
  public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
    return http
//    O Auth logic
//        .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/api/**"))
//        .authorizeExchange((exchanges) -> exchanges.anyExchange().authenticated())
//        .oauth2ResourceServer(OAuth2ResourceServerSpec::jwt);

        // @formatter:off
        .csrf(CsrfSpec::disable)
        .cors(CorsSpec::disable)
        .authorizeExchange(exchanges -> exchanges
            .pathMatchers("/","/resources/**", "/user/signup", "/about", "/auth/login").permitAll()
            .pathMatchers("/admin/**").hasRole("ADMIN")
            .anyExchange().authenticated())
        .exceptionHandling(exceptionHandlingSpec -> exceptionHandlingSpec
            .authenticationEntryPoint((serverWebExchange, accessDeniedException) ->
                Mono.fromRunnable(() -> serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED)))
            .accessDeniedHandler((serverWebExchange, accessDeniedException) ->
                Mono.fromRunnable(() -> serverWebExchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN))))
        .httpBasic(HttpBasicSpec::disable)
        .formLogin(FormLoginSpec::disable)
        .logout(LogoutSpec::disable)
//      .logout(logout -> logout.logoutHandler( (webFilterExchange, authentication) ->
//          Mono.fromRunnable(() -> webFilterExchange.getExchange())))
        .build();
    // @formatter:on
  }

  /*
  @Bean
  ApplicationListener<AuthenticationSuccessEvent> successEventApplicationListener() {
    return event ->
        System.out.println("Login Success." + event.getAuthentication().getName()
            + " - " + event.getAuthentication().getAuthorities());
  }

  @Bean
  ApplicationListener<AbstractAuthenticationFailureEvent> failureEvent() {
    return event ->
        System.out.println("Login Failed." + event.getAuthentication().getName()
            + " - " + event.getAuthentication().getAuthorities());
  }
  */
}
