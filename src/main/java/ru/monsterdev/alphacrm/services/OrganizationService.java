package ru.monsterdev.alphacrm.services;

import ru.monsterdev.alphacrm.domain.OrganizationEntity;
import ru.monsterdev.alphacrm.model.TryitForm;

/**
 * Сервис по работе с организациями
 * @author madmax
 */
public interface OrganizationService {

  /**
   * Создает новую организацию, используемую для тестовых целей
   * @return тестовая организация
   */
  OrganizationEntity createDemoOrganization(TryitForm tryitForm);
}
