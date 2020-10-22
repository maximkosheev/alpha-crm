package ru.monsterdev.alphacrm.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.monsterdev.alphacrm.domain.OrganizationEntity;
import ru.monsterdev.alphacrm.model.OrganizationData;
import ru.monsterdev.alphacrm.repository.OrganizationRepository;
import ru.monsterdev.alphacrm.services.OrganizationService;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

  private final OrganizationRepository repository;

  @Override
  @Transactional
  public OrganizationEntity createOrganization(OrganizationData organization) {
    log.debug("Создание организации: ", organization.toString());
    OrganizationEntity org = new OrganizationEntity();
    org.setName(organization.getName());
    org.setTariffId(organization.getTariff().getId());
    return repository.save(org);
  }
}
