package ru.monsterdev.alphacrm.validators;

import java.util.Set;
import javax.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.monsterdev.alphacrm.exceptions.ReCaptchaInvalidException;
import ru.monsterdev.alphacrm.exceptions.ReCaptchaUnavailableException;
import ru.monsterdev.alphacrm.model.TryitForm;
import ru.monsterdev.alphacrm.services.CaptchaService;

@Service
@Qualifier("tryItFormValidator")
public class TryItFormValidator implements Validator {

  @Autowired
  private javax.validation.Validator javaValidator;

  @Autowired
  private CaptchaService captchaService;

  @Override
  public boolean supports(Class<?> clazz) {
    return TryitForm.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    Set<ConstraintViolation<Object>> violations = javaValidator.validate(target);

    // Выполняем валидацию по аннотациям
    violations.forEach(violation -> errors.rejectValue(violation.getPropertyPath().toString(), violation.getMessage()));
    // Выполняем валидацию остальных полей
    TryitForm form = (TryitForm) target;
    try {
      captchaService.processResponse(form.getCaptcha());
    } catch (ReCaptchaInvalidException | ReCaptchaUnavailableException ex) {
      errors.rejectValue("captcha", "validation.error.captcha");
    }
  }
}
