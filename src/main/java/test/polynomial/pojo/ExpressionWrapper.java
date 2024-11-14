package test.polynomial.pojo;

/**
 * Represents wrapper on polynomial expression passed as String
 */
public class ExpressionWrapper {
    /**
     * Passed expression
     */
    private String expression;
    /**
     * Value used in parsing process
     */
    private int index;

    public ExpressionWrapper(String expression) {
        this.expression = expression;
        index = 0;
    }

    public String getExpression() {
        return expression;
    }

    public ExpressionWrapper setExpression(String expression) {
        this.expression = expression;
        return this;
    }

    public int getIndex() {
        return index;
    }

    public ExpressionWrapper setIndex(int index) {
        this.index = index;
        return this;
    }
}
