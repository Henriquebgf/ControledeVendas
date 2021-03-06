
package br.edu.iff.ControledeVendas.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = NomeValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NomeValidation {
    String message() default "Nome inválido (não pode conter números)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
