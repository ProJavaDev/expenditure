package expenditure.expenditure.service;


import expenditure.expenditure.dto.ConsumptionDto;
import expenditure.expenditure.entity.ConsumptionHistory;

import java.util.List;

public interface ConsumptionService {

    ConsumptionDto add(ConsumptionDto dto);

    ConsumptionDto edit(Long id, ConsumptionDto dto);

    List<ConsumptionDto> findAll();

    List<ConsumptionDto> findAllEdit();

    List<ConsumptionDto> getMyConsumptions();

    List<ConsumptionDto> getMyEditConsumptions(Long id);

}