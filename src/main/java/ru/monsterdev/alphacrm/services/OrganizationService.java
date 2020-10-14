package ru.monsterdev.alphacrm.services;

import ru.monsterdev.alphacrm.domain.OrganizationEntity;

/**
 * Сервис по работе с организациями
 * @author madmax
 */
public interface OrganizationService {

  /**
   * Создает новую организацию, используемую для тестовых целей
   * @return тестовая организация
   */
  OrganizationEntity createDemoOrganization();
}
