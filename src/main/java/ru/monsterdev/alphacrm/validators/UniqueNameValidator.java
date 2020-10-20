package ru.monsterdev.alphacrm.validators;

import javax.sql.DataSource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
public class UniqueNameValidator implements ConstraintValidator<UniqueName, String> {

  @Autowired
  private DataSource dataSource;

  private String queryString;

  @Override
  public void initialize(UniqueName constraintAnnotation) {
    String tableName = constraintAnnotation.tableName();
    String fieldName = constraintAnnotation.fieldName();
    String schema = constraintAnnotation.schema();

    queryString = String.format("SELECT COUNT(*) from %s.%s WHERE lower(%s) = lower(?)",
        schema,
        tableName,
        fieldName);
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    try {
      JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
      Integer count = jdbcTemplate.queryForObject(queryString, new Object[] {value}, Integer.class);
      return count == 0;
    } catch (Exception ex) {
      log.error(ex.getMessage(), ex);
      return false;
    }
  }
}
