
package br.edu.iff.ControledeVendas.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = SemEspacoValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SemEspacoValidation {
    String message() default "Não aceita espaço em branco";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
