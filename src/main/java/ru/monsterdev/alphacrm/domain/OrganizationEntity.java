package ru.monsterdev.alphacrm.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "op_organization", schema = "alpha")
@SequenceGenerator(name = "organization_gen", schema = "alpha", sequenceName = "op_organization_seq",
    allocationSize = 1)
public class OrganizationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organization_gen")
  private Long id;
  @Column(name = "name")
  private String name;
  @Column(name = "tariff_id")
  private Integer tariffId;
  @Column(name = "create_date")
  private Date createDate;
}
