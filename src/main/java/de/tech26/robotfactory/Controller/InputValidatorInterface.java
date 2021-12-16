package de.tech26.robotfactory.Controller;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RequestData.class)

public @interface InputValidatorInterface {
    String message() default "Hello";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
