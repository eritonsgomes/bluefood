package br.com.softblue.bluefood.infrastructure.web.validator;

import br.com.softblue.bluefood.util.FileType;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Constraint(validatedBy = LogotipoRestauranteUploadValidator.class)
public @interface LogotipoRestauranteUploadConstraint {

    String message() default "A Extensão do Arquivo não é permitida";
    FileType[] acceptedTypes() default {};
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
