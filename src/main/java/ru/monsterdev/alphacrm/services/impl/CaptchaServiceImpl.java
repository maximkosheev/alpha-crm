package ru.monsterdev.alphacrm.services.impl;

import java.net.URI;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ru.monsterdev.alphacrm.exceptions.ReCaptchaInvalidException;
import ru.monsterdev.alphacrm.exceptions.ReCaptchaUnavailableException;
import ru.monsterdev.alphacrm.model.dto.GoogleResponseDto;
import ru.monsterdev.alphacrm.services.CaptchaService;
import ru.monsterdev.alphacrm.services.ReCaptchaAttemptService;

@Slf4j
@Service
@RequiredArgsConstructor
public class CaptchaServiceImpl implements CaptchaService {

  @Value("${google.reCAPTCHA.secret-key}")
  private String secretKey;

  @Autowired
  private HttpServletRequest request;

  private static final Pattern RESPONSE_PATTERN = Pattern.compile("[A-Za-z0-9_-]+");
  private static final String RECAPTCHA_URL_TEMPLATE = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s&remoteip=%s";

  private final ReCaptchaAttemptService reCaptchaAttemptService;

  private String getClientIP() {
    String xfHeader = request.getHeader("X-Forwarded-For");
    if (xfHeader == null) {
      return request.getRemoteAddr();
    } else {
      return xfHeader.split(",")[0];
    }
  }

  @Override
  public void processResponse(String response) throws ReCaptchaInvalidException {
    String clientIP = getClientIP();
    log.debug("Attempting to validate response: {}, ip {}", response, clientIP);
    checkSecurity(response, clientIP);
    final URI verifyUri = URI.create(String.format(RECAPTCHA_URL_TEMPLATE,
        secretKey, response, clientIP));
    try {
      RestTemplate restTemplate = new RestTemplate();
      final GoogleResponseDto googleResponse = restTemplate.getForObject(verifyUri, GoogleResponseDto.class);
      log.debug("Google's response: {} ", googleResponse.toString());
      if (!googleResponse.isSuccess()) {
        if (googleResponse.hasClientError()) {
          reCaptchaAttemptService.reCaptchaFailed(clientIP);
        }
        throw new ReCaptchaInvalidException("Проверка не прошла, попробуйте еще раз");
      }
    } catch (RestClientException ex) {
      log.error(ex.getMessage(), ex);
      throw new ReCaptchaUnavailableException("Регистрация в данный момент недоступна.  Пожалуйста, попробуйте позже.", ex);
    }
    reCaptchaAttemptService.reCaptchaSuccess(clientIP);
  }

  private void checkSecurity(String response, String clientIP) {
    if (reCaptchaAttemptService.isBlocked(clientIP)) {
      throw new ReCaptchaInvalidException("Достигнуто максимальное кол-во неуспешных попыток выполнения reCaptcha");
    }

    if (!RESPONSE_PATTERN.matcher(response).matches()) {
      throw new ReCaptchaInvalidException("Ответ содержит недопустимые символы");
    }
  }
}
