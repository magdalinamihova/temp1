package at.spengergasse.sj2324posproject.foundation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordMatchingValidator.class)

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatching {
    String passwordAttribute() default "password";

    String confirmPasswordAttribute() default "confirmPassword";

    String message() default "Password must match!";

    Class<?>[] groups() default {};

    Class<?extends Payload>[] payload() default {};

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        PasswordMatching[] value();
    }

}
