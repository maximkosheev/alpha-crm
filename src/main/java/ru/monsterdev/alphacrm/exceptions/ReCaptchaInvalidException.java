package ru.monsterdev.alphacrm.exceptions;

public class ReCaptchaInvalidException extends RuntimeException {

  public ReCaptchaInvalidException() {
    super();
  }

  public ReCaptchaInvalidException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public ReCaptchaInvalidException(final String message) {
    super(message);
  }

  public ReCaptchaInvalidException(final Throwable cause) {
    super(cause);
  }
}
