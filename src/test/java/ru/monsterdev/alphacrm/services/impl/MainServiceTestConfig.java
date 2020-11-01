package ru.monsterdev.alphacrm.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class MainServiceTestConfig {

  @Value("${spring.mail.templates.path}")
  private String mailTemplatesPath;

  @Bean
  public ITemplateResolver thymeleafClassLoaderTemplateResolver() {
    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
    templateResolver.setPrefix(mailTemplatesPath + "/");
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode("HTML");
    templateResolver.setCharacterEncoding("UTF-8");
    return templateResolver;
  }
}
