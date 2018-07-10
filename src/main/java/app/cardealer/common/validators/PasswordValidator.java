package app.cardealer.common.validators;

import app.cardealer.common.annotations.PasswordValidation;
import app.cardealer.models.binding.UserRegisterBindingModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValidation, Object> {
    @Override
    public boolean isValid(Object candidate, ConstraintValidatorContext context) {
        UserRegisterBindingModel userBindingModel = (UserRegisterBindingModel) candidate;
        return userBindingModel.getPassword().equals(userBindingModel.getConfirmPassword());
    }
}
