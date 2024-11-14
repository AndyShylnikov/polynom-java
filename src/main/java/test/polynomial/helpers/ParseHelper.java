package test.polynomial.helpers;

import test.polynomial.pojo.ExpressionWrapper;
import test.polynomial.pojo.Polynomial;
import test.polynomial.pojo.Term;

/**
 * Helper class for expession parsing
 */
public class ParseHelper {

    /**
     * Parses expression and returns parsed and simplified polynomial
     * @param wrapper - given wrapper with string expression inside
     * @return parsed and simplified polynomial
     */
    public static Polynomial parseExpression(ExpressionWrapper wrapper) {
        Polynomial result = parseTerm(wrapper);

        while (wrapper.getIndex() < wrapper.getExpression().length()) {
            char operator = wrapper.getExpression().charAt(wrapper.getIndex());
            if (operator == '+' || operator == '-') {
                incrementIndex(wrapper);
                Polynomial nextTerm = parseTerm(wrapper);
                if (operator == '+') {
                    result = PolynomialHelper.add(result, nextTerm);
                } else {
                    result = PolynomialHelper.subtract(result, nextTerm);
                }
            } else {
                break;
            }
        }
        return PolynomialHelper.simplify(result);
    }


    /**
     * Parses terms
     * @param wrapper given with string expression inside
     * @return polynomial
     */
    public static Polynomial parseTerm(ExpressionWrapper wrapper) {
        Polynomial result = parseFactor(wrapper);

        while (wrapper.getIndex() < wrapper.getExpression().length()) {
            char operator = wrapper.getExpression().charAt(wrapper.getIndex());
            if (operator == '*') {
                incrementIndex(wrapper);
                Polynomial nextFactor = parseFactor(wrapper);
                result = PolynomialHelper.multiply(result, nextFactor);
            } else {
                break;
            }
        }
        return result;
    }

    /**
     * Parses factor with handling nested parentheses and variables/constants
     * @param wrapper given with string expression inside
     * @return polynomial
     */
    public static Polynomial parseFactor(ExpressionWrapper wrapper) {
        char currentChar = wrapper.getExpression().charAt(wrapper.getIndex());

        if (currentChar == '(') { // Parse nested expression
            incrementIndex(wrapper);
            Polynomial result = parseExpression(wrapper);
            if (wrapper.getIndex() < wrapper.getExpression().length() && wrapper.getExpression().charAt(wrapper.getIndex()) == ')') {
                incrementIndex(wrapper); // Skip closing parenthesis
            }
            return result;
        } else if (Character.isDigit(currentChar) || currentChar == 'x') { // Parse constant or variable
            return parsePolynomial(wrapper);
        }

        throw new IllegalArgumentException("Unexpected character: " + currentChar);
    }

    /**
     * Parse a polynomial terms like "x", "2*x", "3*x^2", or a constant "5"
     * @param wrapper given with string expression inside
     * @return polynomial
     */
    public static Polynomial parsePolynomial(ExpressionWrapper wrapper) {
        int coefficient = 1;
        int exponent = 0;

        if (Character.isDigit(wrapper.getExpression().charAt(wrapper.getIndex()))) {
            int start = wrapper.getIndex();
            while (wrapper.getIndex() < wrapper.getExpression().length() && Character.isDigit(wrapper.getExpression().charAt(wrapper.getIndex()))) {
                incrementIndex(wrapper);
            }
            coefficient = Integer.parseInt(wrapper.getExpression().substring(start, wrapper.getIndex()));
        }

        if (wrapper.getIndex() < wrapper.getExpression().length() && wrapper.getExpression().charAt(wrapper.getIndex()) == 'x') {
            exponent = 1;
            incrementIndex(wrapper);
            if (wrapper.getIndex() < wrapper.getExpression().length() && wrapper.getExpression().charAt(wrapper.getIndex()) == '^') {
                incrementIndex(wrapper); //Skip '^' symbol
                int start = wrapper.getIndex();
                while (wrapper.getIndex() < wrapper.getExpression().length() && Character.isDigit(wrapper.getExpression().charAt(wrapper.getIndex()))) {
                    incrementIndex(wrapper);
                }
                exponent = Integer.parseInt(wrapper.getExpression().substring(start, wrapper.getIndex()));
            }
        }

        Polynomial polynomial = new Polynomial();
        PolynomialHelper.addTerm(polynomial, new Term(coefficient, exponent));
        return polynomial;
    }

    /**
     * Increments index inside given expression wrapper
     * @param wrapper - given expression wrapper
     */
    private static void incrementIndex(ExpressionWrapper wrapper) {
        int index = wrapper.getIndex();
        index++;
        wrapper.setIndex(index);

    }
}
