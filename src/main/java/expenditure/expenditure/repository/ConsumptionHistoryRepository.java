package expenditure.expenditure.repository;

import expenditure.expenditure.entity.ConsumptionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ConsumptionHistoryRepository extends JpaRepository<ConsumptionHistory, Long> {

    List<ConsumptionHistory> findByConsumption_Id(Long id);
}
