package expenditure.expenditure.repository;

import expenditure.expenditure.entity.ConsumptionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;


public interface ConsumptionHistoryRepository extends JpaRepository<ConsumptionHistory, Long> {
    ConsumptionHistory getByCreatedDate(Long date);
    List<ConsumptionHistory> findByConsumption_Id(Long id);
    List<ConsumptionHistory> findByCreatedDateGreaterThanAndCreatedDateLessThan(Date date1, Date date);
    List<ConsumptionHistory> findByCreatedDateGreaterThanAndCreatedDateLessThanAndUserId(Date date1, Date date, Long userId);

}
