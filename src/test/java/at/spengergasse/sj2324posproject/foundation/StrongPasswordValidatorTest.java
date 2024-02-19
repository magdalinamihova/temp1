package at.spengergasse.sj2324posproject.foundation;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class StrongPasswordValidatorTest {

    private StrongPasswordValidator validator;
    private @Mock ConstraintValidatorContext ctx;

    @BeforeEach
    void setup() {
        validator = new StrongPasswordValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "!VerySecret2024",
            "!2024Ultrasecret"
    })
    void ensureStrongPasswordsAreValidatedAsValid(String password) {
        // expected
        assertThat(validator.isValid(password, ctx)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "!Secret2024",
            "_Secret2024_",
            "AAAAAAAAAAAA",
            "abcabcabcabc",
            "123412341234"
    })
    void ensureWeakPasswordsAreValidatedAsInvalid(String password) {
        // expected
        assertThat(validator.isValid(password, ctx)).isFalse();
    }
}