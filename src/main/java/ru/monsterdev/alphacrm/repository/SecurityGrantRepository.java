package ru.monsterdev.alphacrm.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import ru.monsterdev.alphacrm.domain.SecurityGrantEntity;

public interface SecurityGrantRepository extends CrudRepository<SecurityGrantEntity, Long> {

  @Query(value = "SELECT nextval('alpha.subject_id_seq')")
  long getNextSubjectId();

  @Query(value = "SELECT nextval('alpha.object_id_seq')")
  long getNextObjectId();

}
