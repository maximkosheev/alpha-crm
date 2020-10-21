package ru.monsterdev.alphacrm.domain;

import java.util.Collection;
import java.util.Date;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class AlphaUser implements UserDetails {

  @Id
  private Long id;
  private String firstname;
  private String lastname;
  private String middletname;
  private Long orgId;
  private String email;
  private String password;
  private String phone;
  private Integer roleId;
  private Long subjectId;
  private Date createDate;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
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
}
