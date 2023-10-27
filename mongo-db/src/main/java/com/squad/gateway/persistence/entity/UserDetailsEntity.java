package com.squad.gateway.persistence.entity;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "Users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDetailsEntity implements Serializable {

  @MongoId
  UUID id;

  String name;

  // email
  @Indexed(unique = true)
  String username;

  String password;

  // List of role id ints
  List<String> authorities;

  boolean enabled;

  // Signup date
  // Update date
  // Created by

  boolean accountNonExpired;

  // Duration for locking ??
  boolean accountNonLocked;

  boolean credentialsNonExpired;
}
