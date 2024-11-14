package test.polynomial.models;

import jakarta.persistence.*;

@Entity
@Table(name = "expressions", uniqueConstraints = {
        @UniqueConstraint(columnNames = "original")
})
public class PolynomialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "original", nullable = false)
    private String originalExpression;
    @Column(name = "simplified", nullable = false)
    private String simplifiedExpression;

    public PolynomialEntity() {
    }

    public PolynomialEntity(String originalExpression, String simplifiedExpression) {
        this.originalExpression = originalExpression;
        this.simplifiedExpression = simplifiedExpression;
    }

    public Integer getId() {
        return Id;
    }

    public PolynomialEntity setId(Integer id) {
        Id = id;
        return this;
    }

    public String getOriginalExpression() {
        return originalExpression;
    }

    public PolynomialEntity setOriginalExpression(String originalExpression) {
        this.originalExpression = originalExpression;
        return this;
    }

    public String getSimplifiedExpression() {
        return simplifiedExpression;
    }

    public PolynomialEntity setSimplifiedExpression(String simplifiedExpression) {
        this.simplifiedExpression = simplifiedExpression;
        return this;
    }
}
