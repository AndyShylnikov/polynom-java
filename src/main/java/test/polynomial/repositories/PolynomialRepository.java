package test.polynomial.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.polynomial.models.PolynomialEntity;

import java.util.Optional;
@Repository
public interface PolynomialRepository extends JpaRepository<PolynomialEntity, Integer> {
    Optional<PolynomialEntity> findByOriginalExpression(String original);
}
