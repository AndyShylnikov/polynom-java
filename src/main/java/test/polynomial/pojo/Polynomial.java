package test.polynomial.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Polynomial class represents wrapper on list of terms
 */
public class Polynomial {

    /**
     * terms list
     */
    private List<Term> termList;

    public Polynomial() {
        this.termList = new ArrayList<>();
    }

    public List<Term> getTermList() {
        return termList;
    }

    public Polynomial setTermList(List<Term> termList) {
        this.termList = termList;
        return this;
    }
}
