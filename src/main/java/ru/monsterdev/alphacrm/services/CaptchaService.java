package ru.monsterdev.alphacrm.services;

import ru.monsterdev.alphacrm.exceptions.ReCaptchaInvalidException;

public interface CaptchaService {
  void processResponse(final String response) throws ReCaptchaInvalidException;
}
