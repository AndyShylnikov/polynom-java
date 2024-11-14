package test.polynomial.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.polynomial.helpers.ParseHelper;
import test.polynomial.helpers.PolynomialHelper;
import test.polynomial.helpers.StringHelper;
import test.polynomial.interfaces.IPolynomialService;
import test.polynomial.models.PolynomialEntity;
import test.polynomial.pojo.ExpressionWrapper;
import test.polynomial.pojo.Polynomial;
import test.polynomial.repositories.PolynomialRepository;

import java.util.Optional;

/**
 * Implementation of {@link IPolynomialService}
 */
@Service
public class PolynomialService implements IPolynomialService {

    @Autowired
    private PolynomialRepository repository;

    /**
     * Parses given expression
     *
     * @param expression - string expression to parse
     * @return parsed result
     */
    @Override
    public String parse(String expression) {
        Optional<PolynomialEntity> foundEntity = repository.findByOriginalExpression(expression);
        if (foundEntity.isEmpty()){

            String simplified = StringHelper.polymonialToString(parseToPolynomial(expression));
            PolynomialEntity newEntity = new PolynomialEntity(expression, simplified);
            repository.save(newEntity);
            return simplified;
        }
        else {
            return foundEntity.get().getSimplifiedExpression();
        }

    }

    /**
     * Parses given expression and solve it as function
     *
     * @param expression - string expression to parse
     * @param argumentValue          - given argument
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
        expression = expression.replaceAll("\\s+", "").replaceAll("−","-"); // Remove all whitespace
        ExpressionWrapper wrapper = new ExpressionWrapper(expression);
        return ParseHelper.parseExpression(wrapper);
    }
}