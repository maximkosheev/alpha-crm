package ru.monsterdev.alphacrm.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.monsterdev.alphacrm.model.UserData;
import ru.monsterdev.alphacrm.services.MailService;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

  @Override
  public void sendNewUserMessage(UserData userData) {

  }
}
