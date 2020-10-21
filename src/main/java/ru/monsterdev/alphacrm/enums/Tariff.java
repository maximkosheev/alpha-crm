package ru.monsterdev.alphacrm.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Tariff {
  DEMO(1, "Демо"),
  ECONOM(2, "Эконом"),
  FREELANCE(3, "Фриланс"),
  PREMIUM(4, "Премиум"),
  CUSTOM(5, "Индивидуальный");

  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
