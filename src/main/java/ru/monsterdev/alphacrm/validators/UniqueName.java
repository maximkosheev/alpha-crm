package ru.monsterdev.alphacrm.validators;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueNameValidator.class)
@Documented
public @interface UniqueName {

  Class<?>[] groups() default { };

  Class<? extends Payload>[] payload() default { };

  /**
   * Схема базы данных
   * @return
   */
  String schema() default "alpha";

  /**
   * Имя таблицы, в которой нужно поискать записи
   */
  String tableName();

  /**
   * Поле в таблице {@code tableName}, которое должно быть уникальным
   */
  String fieldName();

  /**
   * Сообщение об ошибке
   */
  String message() default "Поле должно быть уникальным";
}
