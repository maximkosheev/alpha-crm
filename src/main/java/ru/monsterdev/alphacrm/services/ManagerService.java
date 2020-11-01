package ru.monsterdev.alphacrm.services;

import ru.monsterdev.alphacrm.domain.AlphaUser;
import ru.monsterdev.alphacrm.exceptions.BusinessLogicException;
import ru.monsterdev.alphacrm.model.TryitForm;

public interface ManagerService {

  AlphaUser createDemoAccount(TryitForm accountInfo) throws BusinessLogicException;
}
