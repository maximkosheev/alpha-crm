package ru.monsterdev.alphacrm.domain;

import java.util.Date;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserEntity {
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
}
