package ru.monsterdev.alphacrm.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.monsterdev.alphacrm.domain.UserEntity;
import ru.monsterdev.alphacrm.model.AlphaUser;
import ru.monsterdev.alphacrm.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity user = repository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь %s не найден", username)));

    AlphaUser alphaUser = new AlphaUser();
    return alphaUser;
  }
}
