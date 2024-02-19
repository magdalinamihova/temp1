package at.spengergasse.sj2324posproject.foundation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordMatchingValidator implements ConstraintValidator<PasswordMatching, Object> {

    private String passwordAttribute;
    private String confirmPasswordAttribute;

    @Override
    public void initialize(PasswordMatching passwordMatchingAnnotation) {
        this.passwordAttribute = passwordMatchingAnnotation.passwordAttribute();
        this.confirmPasswordAttribute = passwordMatchingAnnotation.confirmPasswordAttribute();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        Object passwordValue = new BeanWrapperImpl(value).getPropertyValue(passwordAttribute);
        Object confirmPasswordValue = new BeanWrapperImpl(value).getPropertyValue(confirmPasswordAttribute);

        return (passwordValue != null) ? passwordValue.equals(confirmPasswordValue) : confirmPasswordValue == null;
    }
}
