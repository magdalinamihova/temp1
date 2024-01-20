package at.spengergasse.sj2324posproject.foundation;

import org.junit.jupiter.api.Test;

import static at.spengergasse.sj2324posproject.foundation.Ensurer.isNotNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EnsurerTest {
    @Test
    void ensureIsNotNullWorks() {
        assertThat(isNotNull("is not null")).isEqualTo("is not null");
        var iaEx = assertThrows(IllegalArgumentException.class, () -> isNotNull(null));
        assertThat(iaEx).hasMessageContaining("argument must not be null!");
    }
}