package ru.monsterdev.alphacrm.model;

import lombok.Data;

@Data
public class UserData {
  private String firstname;
  private String lastname;
  private String middlename;
  private Long orgId;
  private String email;
  private String password;
  private String phone;
  private Integer roleId;
  private Long subjectId;
}
