package test.polynomial.services;

import test.polynomial.helpers.ParseHelper;
import test.polynomial.helpers.PolynomialHelper;
import test.polynomial.helpers.StringHelper;
import test.polynomial.interfaces.IPolynomialService;
import test.polynomial.pojo.ExpressionWrapper;
import test.polynomial.pojo.Polynomial;

/**
 * Implementation of {@link IPolynomialService}
 */
public class PolynomialService implements IPolynomialService{

    /**
     * Parses given expression
     *
     * @param expression - string expression to parse
     * @return parsed result
     */
    @Override
    public String parse(String expression) {
        return StringHelper.polymonialToString(parseToPolynomial(expression));
    }

    /**
     * Parses given expression and solve it as function
     *
     * @param expression - string expression to parse
     * @param x          - given argument
     * @return result of function
     */
    @Override
    public int solve(String expression, int x) {
        return PolynomialHelper.solve(parseToPolynomial(expression), x);
    }

    /**
     * Parses given expression
     *
     * @param expression - string expression to parse
     * @return parsed result
     */
    private Polynomial parseToPolynomial(String expression){
        expression = expression.replaceAll("\\s+", ""); // Remove all whitespace
        ExpressionWrapper wrapper =new ExpressionWrapper(expression);
        return ParseHelper.parseExpression(wrapper);
    }
}