package ru.monsterdev.alphacrm.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.thymeleaf.spring5.SpringTemplateEngine;
import ru.monsterdev.alphacrm.config.MailConfig;
import ru.monsterdev.alphacrm.services.MailService;

@ExtendWith(SpringExtension.class)
@DirtiesContext
@ContextConfiguration(classes = {MailConfig.class, MainServiceTestConfig.class},
    initializers = {ConfigFileApplicationContextInitializer.class})
class MailServiceImplTest {

  @Autowired
  private JavaMailSender mailSender;

  @Autowired
  private SpringTemplateEngine thymeleafTemplateEngine;

  private MailService mailService;

  @BeforeEach
  public void init() {
    mailService = new MailServiceImpl(mailSender, thymeleafTemplateEngine);
  }

}