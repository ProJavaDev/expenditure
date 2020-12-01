package expenditure.expenditure.service;

import expenditure.expenditure.dto.ConsumptionDto;
import java.util.List;

public interface ConsumptionService {

    ConsumptionDto add(ConsumptionDto dto);

    ConsumptionDto edit(Long id, ConsumptionDto dto);

    List<ConsumptionDto> findAll() ;

    List<ConsumptionDto> findAllEdit() ;

    List<ConsumptionDto> getMyConsumptions();

    List<ConsumptionDto> getConsumptionHistoryById(Long id);

    List<ConsumptionDto> getConsumptionHistoryByDate(Long date, Long date2);

    List<ConsumptionDto> getConsumptionByDate(Long date, Long date2);

    List<ConsumptionDto> getConsumptionByGroupId(Long id);

}