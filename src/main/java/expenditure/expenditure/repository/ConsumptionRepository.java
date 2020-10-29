package expenditure.expenditure.repository;

import expenditure.expenditure.entity.Consumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ConsumptionRepository extends JpaRepository<Consumption, Long> {

    Consumption getById(Long id);

    List<Consumption> findByUserId(Long userId);

}
