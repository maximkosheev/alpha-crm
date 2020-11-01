package ru.monsterdev.alphacrm.services.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Service;
import ru.monsterdev.alphacrm.services.ReCaptchaAttemptService;

@Service
public class ReCaptchaAttemptServiceImpl implements ReCaptchaAttemptService {
  private final int MAX_ATTEMPT = 4;
  private LoadingCache<String, Integer> attemptsCache;

  public ReCaptchaAttemptServiceImpl() {
    super();
    attemptsCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.HOURS).build(
        new CacheLoader<String, Integer>() {
          @Override
          public Integer load(String s) throws Exception {
            return 0;
          }
        });
  }

  @Override
  public void reCaptchaSuccess(String clientIP) {
    attemptsCache.invalidate(clientIP);
  }

  @Override
  public void reCaptchaFailed(String clientIP) {
    int attempts = attemptsCache.getUnchecked(clientIP);
    attempts++;
    attemptsCache.put(clientIP, attempts);
  }

  @Override
  public boolean isBlocked(String clientIP) {
    return attemptsCache.getUnchecked(clientIP) >= MAX_ATTEMPT;
  }
}
