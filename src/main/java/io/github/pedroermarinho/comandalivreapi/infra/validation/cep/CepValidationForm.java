package io.github.pedroermarinho.comandalivreapi.infra.validation.cep;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CepValidatorForm.class)
public @interface CepValidationForm {

    String message() default "Username invalido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
