package ru.monsterdev.alphacrm.services;

/**
 * Сервис проверки неудачных попыток прохождения reCaptcha клиентом
 */
public interface ReCaptchaAttemptService {

  /**
   * Сбрасывает счетчик неудачных попыток проверки reCaptcha для адреса клиента {@code clientIP}
   * @param clientIP - адрес клиента
   */
  void reCaptchaSuccess(String clientIP);
  /**
   * Увеличивает счетчик неудачных попыток проверки reCaptcha для адреса клиента {@code clientIP}
   * @param clientIP - адрес клиента
   */
  void reCaptchaFailed(String clientIP);

  /**
   * Выполняет проверку того, что адрес клиента не заблокирован
   * @param clientIP адрес клиента
   * @return заблокирован - true, иначе false
   */
  boolean isBlocked(String clientIP);
}
