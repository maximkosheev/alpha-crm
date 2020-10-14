package ru.monsterdev.alphacrm.domain;

import java.util.Date;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class OrganizationEntity {
  @Id
  private Long id;
  private String name;
  private Integer tariffId;
  private Date createDate;
}
