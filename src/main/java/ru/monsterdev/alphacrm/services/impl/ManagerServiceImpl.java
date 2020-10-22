package ru.monsterdev.alphacrm.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.monsterdev.alphacrm.domain.AlphaUser;
import ru.monsterdev.alphacrm.domain.OrganizationEntity;
import ru.monsterdev.alphacrm.enums.Tariff;
import ru.monsterdev.alphacrm.enums.UserRole;
import ru.monsterdev.alphacrm.model.OrganizationData;
import ru.monsterdev.alphacrm.model.TryitForm;
import ru.monsterdev.alphacrm.model.UserData;
import ru.monsterdev.alphacrm.services.MailService;
import ru.monsterdev.alphacrm.services.ManagerService;
import ru.monsterdev.alphacrm.services.OrganizationService;
import ru.monsterdev.alphacrm.services.SecurityService;
import ru.monsterdev.alphacrm.services.UserService;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

  private final OrganizationService organizationService;
  private final UserService userService;
  private final SecurityService securityService;
  private final MailService mailService;

  @Override
  @Transactional
  public void createDemoAccount(TryitForm accountInfo) {
    try {
      log.debug("Создание демо-аккаунта", accountInfo.toString());
      // Создание демо-организации
      OrganizationData orgData = new OrganizationData();
      orgData.setName(accountInfo.getOrgName());
      orgData.setTariff(Tariff.DEMO);
      OrganizationEntity orgEntity = organizationService.createOrganization(orgData);
      // Создание пользователя в ней
      UserData userData = new UserData();
      userData.setFirstname(accountInfo.getFirstname());
      userData.setLastname(accountInfo.getLastname());
      userData.setMiddlename(accountInfo.getMiddlename());
      userData.setEmail(accountInfo.getEmail());
      userData.setPassword(securityService.generatePassword());
      userData.setRoleId(UserRole.ROLE_ADMIN.getId());
      userData.setSubjectId(securityService.getNextSubjectId());
      userData.setOrgId(orgEntity.getId());
      AlphaUser userEntity = userService.createUser(userData);
      // Отправка пароля пользователя на указанную почту
      mailService.sendNewUserMessage(userData);
    } catch (Exception ex) {
      log.error(ex.getMessage(), ex);
      throw ex;
    }
  }

}
