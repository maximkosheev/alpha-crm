package ru.monsterdev.alphacrm.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(value = "alpha.op_security_grants")
public class SecurityGrantEntity {
  @Id
  private Long id;

}
