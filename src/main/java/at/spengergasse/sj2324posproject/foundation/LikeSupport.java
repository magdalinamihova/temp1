package at.spengergasse.sj2324posproject.foundation;

public interface LikeSupport {

    default String fixLikeExpression(String expression) {
        return (!expression.contains("%"))
                ? String.format("%s%%", expression)
                : expression;
    }
}
