package ru.monsterdev.alphacrm.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.monsterdev.alphacrm.domain.AlphaUser;
import ru.monsterdev.alphacrm.model.UserData;
import ru.monsterdev.alphacrm.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepository repository;
  private final SecurityService securityService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    AlphaUser user = repository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь %s не найден", username)));

    AlphaUser alphaUser = new AlphaUser();
    return alphaUser;
  }

  @Transactional
  public AlphaUser createUser(UserData userInfo) {
    log.debug("Создание нового пользователя ", userInfo.toString());
    AlphaUser entity = new AlphaUser();
    entity.setFirstname(userInfo.getFirstname());
    entity.setLastname(userInfo.getLastname());
    entity.setMiddlename(userInfo.getMiddlename());
    entity.setOrgId(userInfo.getOrgId());
    entity.setEmail(userInfo.getEmail());
    entity.setPassword(securityService.encodePassword(userInfo.getPassword()));
    entity.setPhone(userInfo.getPhone());
    entity.setRoleId(userInfo.getRoleId());
    entity.setSubjectId(userInfo.getSubjectId());
    return repository.save(entity);
  }
}
