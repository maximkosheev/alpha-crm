package ru.monsterdev.alphacrm.enums;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
  ROLE_ADMIN(1, "Администратор организации"),
  ROLE_USER(2, "Обычный пользователь");

  private int id;
  private String description;

  public static UserRole valueById(int id) {
    return Arrays.stream(values())
        .filter(role -> role.id == id)
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Роль не найдена"));
  }
}
