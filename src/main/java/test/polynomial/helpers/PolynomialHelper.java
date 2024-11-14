package test.polynomial.helpers;

import test.polynomial.pojo.Polynomial;
import test.polynomial.pojo.Term;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for operation on Polynomial expressions(adding, substracting and multiplying).
 * Also provides ability to use expression as function f(x) and solve it with given argument
 */
public class PolynomialHelper {
    /**
     * Adds terms to list inside polynomial
     * @param polynomial - given polynomial
     * @param term - term to add
     */
    public static void addTerm(Polynomial polynomial, Term term) {
        List<Term> termList = polynomial.getTermList();
        termList.add(term);
        polynomial.setTermList(termList);
    }

    /**
     * Adds two polynomials and returns result
     * @param first - one of polynomials
     * @param second - other one
     * @return - simplied result of adding
     */
    public static Polynomial add(Polynomial first, Polynomial second) {
        Polynomial result = new Polynomial();
        List<Term> termList = result.getTermList();
        termList.addAll(first.getTermList());
        termList.addAll(second.getTermList());
        result.setTermList(termList);
        return simplify(result);
    }

    /**
     * Substract polynomial from another and returns result
     * @param first - one of polynomials
     * @param second - other one
     * @return - simplied result of substracting
     */
    public static Polynomial subtract(Polynomial first, Polynomial second) {
        Polynomial result = new Polynomial();
        result.getTermList().addAll(first.getTermList());
        for (Term term : second.getTermList()) {
            addTerm(result, new Term(-term.getCoefficient(), term.getExponent()));
        }
        return simplify(result);
    }

    /**
     * Multiplies two polynomials and returns result
     * @param first - one of polynomials
     * @param second - other one
     * @return - simplied result of multiplying
     */
    public static Polynomial multiply(Polynomial first, Polynomial second) {
        Polynomial result = new Polynomial();
        for (Term term1 : first.getTermList()) {
            for (Term term2 : second.getTermList()) {
                addTerm(result, new Term(term1.getCoefficient() * term2.getCoefficient(),
                        term1.getExponent() + term2.getExponent()));
            }
        }
        return simplify(result);
    }

    /**
     * Simplifies polynomial by reducing terms.
     * Coefficients are added if exponents are same, like [3x^2, 4x^2] will be converted to [7x^2]
     * @param polynomial -given polynomial expression
     * @return  result of simplifying
     */
    public static Polynomial simplify(Polynomial polynomial) {
        Map<Integer, Integer> termMap = new HashMap<>();
        for (Term term : polynomial.getTermList()) {
            int exponent = term.getExponent();
            int coefficient = termMap.getOrDefault(exponent, 0) + term.getCoefficient();
            termMap.put(exponent, coefficient);
        }
        Polynomial simplified = new Polynomial();
        for (Map.Entry<Integer, Integer> entry : termMap.entrySet()) {
            if (entry.getValue() != 0) {
                addTerm(simplified, new Term(entry.getValue(), entry.getKey()));
            }
        }
        return simplified;
    }

    /**
     * Treats given polynomial as f(x) function and solves it by given x value
     * @param polynomial - given expression
     * @param x - given x
     * @return function result
     */
    public static int solve(Polynomial polynomial, int x) {
        int result = 0;
        for (Term term : polynomial.getTermList()) {
            result += term.getCoefficient() * (Math.pow(x, term.getExponent()));
        }
        return result;
    }
}
