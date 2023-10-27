package com.squad.gateway.dto.login;


import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginDto {

  @NotBlank(message = "Username must not be blank")
  String username;

  @NotBlank(message = "Password must not be blank")
  String password;
}
