package com.squad.gateway.utility;

import java.util.Collection;
import java.util.Collections;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@UtilityClass
public class DefaultUserUtility {

  public UserDetails getDefaultUser() {
    return new UserDetails() {
      @Override
      public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
      }

      @Override
      public String getPassword() {
        return null;
      }

      @Override
      public String getUsername() {
        return null;
      }

      @Override
      public boolean isAccountNonExpired() {
        return false;
      }

      @Override
      public boolean isAccountNonLocked() {
        return false;
      }

      @Override
      public boolean isCredentialsNonExpired() {
        return false;
      }

      @Override
      public boolean isEnabled() {
        return false;
      }
    };
  }
}
