package ru.monsterdev.alphacrm.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TryitForm {
  @NotNull
  @Size(min = 1, message = "Поле \"Имя\" не может быть пустым")
  private String firstname;
  private String lastname;
  private String middlename;
  @NotNull
  @Size(min = 1, message = "Поле \"Название организации\" не может быть пустым")
  private String orgName;
  @NotNull
  @Size(min = 1, message = "Поле \"Email\" не может быть пустым")
  private String email;
}
