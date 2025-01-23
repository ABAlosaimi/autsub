package com.autsub.autsub.Annos;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidatePassword, String> {
    public void initialize(ValidatePassword constraint) {
    }

    public boolean isValid(String password, ConstraintValidatorContext context) {
        return password != null && password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[A-Za-z\\d@$!%*?&]{8,}$");
    }
}
