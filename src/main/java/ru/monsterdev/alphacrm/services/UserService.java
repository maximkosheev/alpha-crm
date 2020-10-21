package ru.monsterdev.alphacrm.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.monsterdev.alphacrm.domain.AlphaUser;
import ru.monsterdev.alphacrm.model.UserData;
import ru.monsterdev.alphacrm.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    AlphaUser user = repository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь %s не найден", username)));

    AlphaUser alphaUser = new AlphaUser();
    return alphaUser;
  }

  @Transactional
  public AlphaUser createUser(UserData userInfo) {
    AlphaUser entity = new AlphaUser();
    entity.setFirstname(userInfo.getFirstname());
    entity.setLastname(userInfo.getLastname());
    entity.setMiddletname(userInfo.getMiddletname());
    entity.setOrgId(userInfo.getOrgId());
    entity.setEmail(userInfo.getEmail());
    entity.setPassword(userInfo.getPassword());
    entity.setPhone(userInfo.getPhone());
    entity.setRoleId(userInfo.getRoleId());
    entity.setSubjectId(userInfo.getSubjectId());
    return repository.save(entity);
  }
}
