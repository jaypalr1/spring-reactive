package com.squad.gateway.dto.register;

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
public class UserSignupDto {

  @NotBlank
  String username;

  @NotBlank
  String password;

  @NotBlank
  String firstName;

  String lastName;
}
