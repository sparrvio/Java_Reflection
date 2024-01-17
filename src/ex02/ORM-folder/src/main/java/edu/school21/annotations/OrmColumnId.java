package edu.school21.annotations;
import javax.persistence.GenerationType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface OrmColumnId {
    GenerationType id() default GenerationType.AUTO;

    String name();
}
