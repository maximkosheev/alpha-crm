package ru.monsterdev.alphacrm.services;

import ru.monsterdev.alphacrm.domain.OrganizationEntity;
import ru.monsterdev.alphacrm.model.OrganizationData;

/**
 * Сервис по работе с организациями
 * @author madmax
 */
public interface OrganizationService {

  /**
   * Создает новую организацию, используемую для тестовых целей
   * @return сохраненная организация
   */
  OrganizationEntity createOrganization(OrganizationData organization);
}
