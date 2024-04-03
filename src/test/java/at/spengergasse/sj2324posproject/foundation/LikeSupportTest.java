package at.spengergasse.sj2324posproject.foundation;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class LikeSupportTest implements LikeSupport {

    @Test
    void ensureFixLikeExpressionWorks() {
        assertThat(fixLikeExpression("Li")).isEqualTo("Li%");
    }

}
