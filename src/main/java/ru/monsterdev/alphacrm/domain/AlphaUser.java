package ru.monsterdev.alphacrm.domain;

import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Entity
@Table(name = "op_user", schema = "alpha")
@SequenceGenerator(name = "user_gen", schema = "alpha", sequenceName = "op_user_seq",
    allocationSize = 1)
public class AlphaUser implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
  private Long id;
  private String firstname;
  private String lastname;
  private String middlename;
  @Column(name = "org_id")
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
