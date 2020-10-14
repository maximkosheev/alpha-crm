package ru.monsterdev.alphacrm.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.monsterdev.alphacrm.domain.OrganizationEntity;
import ru.monsterdev.alphacrm.repository.OrganizationRepository;
import ru.monsterdev.alphacrm.services.OrganizationService;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

  private final OrganizationRepository repository;

  @Override
  public OrganizationEntity createDemoOrganization() {
    OrganizationEntity org = new OrganizationEntity();
    return repository.save(org);
  }
}
