package com.squad.gateway.dto.response;

import java.util.List;
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
public class UserResponse {

  String name;

  String username;

  boolean accountNonExpired;

  boolean accountNonLocked;

  boolean credentialsNonExpired;

  boolean enabled;
}
