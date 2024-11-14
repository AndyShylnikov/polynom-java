package test.polynomial.pojo;

/**
 * Represents single term in polynomial.
 * In a*x^b coefficient will be responsible for a, and exponent for b
 */
public class Term {

    /**
     * Multiplier coefficient
     * In a*x^b coefficient will be responsible for a
     */
    private int coefficient;
    /**
     * Exponent of x
     * In a*x^b term exponent is responsibe for b
     */
    private int exponent;

    public Term(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public int getExponent() {
        return exponent;
    }
}
