package ru.monsterdev.alphacrm.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import ru.monsterdev.alphacrm.validators.UniqueName;

@Data
public class TryitForm {
  @NotNull
  @Size(min = 1, message = "validation.error.empty")
  private String firstname;
  @NotNull
  @Size(min = 1, message = "validation.error.empty")
  private String lastname;
  private String middlename;
  @NotEmpty(message = "validation.error.empty")
  @UniqueName(tableName = "op_organization", fieldName = "name", message = "validation.error.duplicate")
  private String orgName;
  @NotEmpty(message = "validation.error.empty")
  @UniqueName(tableName = "op_user", fieldName = "email", message = "validation.error.duplicate")
  private String email;
  @NotEmpty(message = "validation.error.empty")
  private String captcha;
}
