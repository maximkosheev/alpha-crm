package ru.monsterdev.alphacrm.model;

import lombok.Data;
import ru.monsterdev.alphacrm.enums.Tariff;

@Data
public class OrganizationData {
  private String name;
  private Tariff tariff;
}
