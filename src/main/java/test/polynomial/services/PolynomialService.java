package test.polynomial.services;

import org.springframework.stereotype.Service;
import test.polynomial.helpers.ParseHelper;
import test.polynomial.helpers.PolynomialHelper;
import test.polynomial.helpers.StringHelper;
import test.polynomial.interfaces.IPolynomialService;
import test.polynomial.pojo.ExpressionWrapper;
import test.polynomial.pojo.Polynomial;

/**
 * Implementation of {@link IPolynomialService}
 */
@Service
public class PolynomialService implements IPolynomialService {

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
    public int solve(String expression, String argumentValue) {
        int x = Integer.parseInt(argumentValue);
        return PolynomialHelper.solve(parseToPolynomial(expression), x);
    }

    /**
     * Parses given expression
     *
     * @param expression - string expression to parse
     * @return parsed result
     */
    private Polynomial parseToPolynomial(String expression) {
        expression = expression.replaceAll("\\s+", ""); // Remove all whitespace
        ExpressionWrapper wrapper = new ExpressionWrapper(expression);
        return ParseHelper.parseExpression(wrapper);
    }
}