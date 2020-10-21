package ru.monsterdev.alphacrm.model;

import java.util.Date;
import lombok.Data;

@Data
public class UserData {
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
