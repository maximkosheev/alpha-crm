package ru.monsterdev.alphacrm.services;

public interface SecurityService {

  /**
   * Генерирует случайный пароль
   * @return пароль
   */
  String generatePassword();

  /**
   * Кодирование пароля
   * @param password пароль, который нужно закодировать
   * @return закодированный пароль
   */
  String encodePassword(String password);

  /**
   * Возвращает очередной идентификатор субъекта безопасности
   * @return идентификатор
   */
  long getNextSubjectId();
}
