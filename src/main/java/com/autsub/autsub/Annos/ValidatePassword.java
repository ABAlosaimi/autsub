package com.autsub.autsub.Annos;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.PARAMETER})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface ValidatePassword {
    
    String message() default "Password must be at least 8 characters long, contain at least one digit, one uppercase letter, one lowercase letter and one special character";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
