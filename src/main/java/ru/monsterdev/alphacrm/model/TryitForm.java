package ru.monsterdev.alphacrm.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import ru.monsterdev.alphacrm.validators.UniqueName;

@Data
public class TryitForm {
  @NotNull
  @Size(min = 1, message = "Поле \"Имя\" не может быть пустым")
  private String firstname;
  @NotNull
  @Size(min = 1, message = "Поле \"Фамилия\" не может быть пустым")
  private String lastname;
  private String middlename;
  @NotNull
  @Size(min = 1, message = "Поле \"Название организации\" не может быть пустым")
  @UniqueName(tableName = "op_organization", fieldName = "name")
  private String orgName;
  @NotNull
  @Size(min = 1, message = "Поле \"Email\" не может быть пустым")
  @UniqueName(tableName = "op_user", fieldName = "email")
  private String email;
}
