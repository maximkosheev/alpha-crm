package ru.monsterdev.alphacrm.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.monsterdev.alphacrm.domain.AlphaUser;

@Repository
public interface UserRepository extends CrudRepository<AlphaUser, Long> {

  Optional<AlphaUser> findByEmail(String login);
}
