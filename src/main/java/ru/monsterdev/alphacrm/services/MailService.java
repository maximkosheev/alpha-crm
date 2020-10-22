package ru.monsterdev.alphacrm.services;

import ru.monsterdev.alphacrm.model.UserData;

public interface MailService {

  /**
   * Отправка сообщения о создании нового пользователя
   * @param userData данные пользователя
   */
  void sendNewUserMessage(UserData userData);
}
