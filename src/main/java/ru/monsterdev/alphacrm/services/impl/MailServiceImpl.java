package ru.monsterdev.alphacrm.services.impl;

import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import ru.monsterdev.alphacrm.model.UserData;
import ru.monsterdev.alphacrm.services.MailService;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

  private final JavaMailSender mailSender;

  private final SpringTemplateEngine thymeleafTemplateEngine;

  @Value("${app.mail.send-from}")
  private String sendFrom;

  private void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException {
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
    helper.setFrom("maximkosheev@gmail.com");
    helper.setTo(to);
    helper.setSubject(subject);
    helper.setText(htmlBody, true);
    mailSender.send(message);
  }

  @Override
  public void sendNewUserMessage(UserData userData) throws MessagingException {
    Map<String, Object> vars = new HashMap<>();
    vars.put("recipientName", userData.getFirstname());
    vars.put("text", "Hello");
    vars.put("password", userData.getPassword());
    Context thymeleafContext = new Context();
    thymeleafContext.setVariables(vars);
    String htmlBody = thymeleafTemplateEngine.process("mail-templates/new-account.html", thymeleafContext);
    sendHtmlMessage(userData.getEmail(), "Добро пожаловать в систему 'Альфа'", htmlBody);
  }
}
