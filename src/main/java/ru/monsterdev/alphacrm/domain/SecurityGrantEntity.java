package ru.monsterdev.alphacrm.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "op_security_grants", schema = "alpha")
public class SecurityGrantEntity {
  @Id
  private Long id;
}
