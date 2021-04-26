package com.company.helthdiary.annotation;

import com.company.helthdiary.validator.UserExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ ElementType.PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = UserExistsValidator.class)
public @interface CheckUserExists {
    String message() default "A user with the same login already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}