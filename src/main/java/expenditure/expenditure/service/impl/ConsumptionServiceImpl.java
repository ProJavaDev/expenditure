package expenditure.expenditure.service.impl;

import expenditure.expenditure.dto.ConsumptionDto;
import expenditure.expenditure.entity.Consumption;
import expenditure.expenditure.entity.ConsumptionHistory;
import expenditure.expenditure.repository.ConsumptionHistoryRepository;
import expenditure.expenditure.repository.ConsumptionRepository;
import expenditure.expenditure.service.ConsumptionService;
import expenditure.expenditure.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ConsumptionServiceImpl implements ConsumptionService {

    private final ConsumptionRepository repository;
    private final UserService userService;
    private final ConsumptionHistoryRepository consumptionHistoryRepository;


    public ConsumptionServiceImpl(ConsumptionRepository consumptionRepository, UserService userService, ConsumptionHistoryRepository consumptionHistoryRepository) {
        this.repository = consumptionRepository;
        this.userService = userService;
        this.consumptionHistoryRepository = consumptionHistoryRepository;
    }

    @Override
    public ConsumptionDto add(ConsumptionDto dto) {
        return ConsumptionDto.toDto(repository.save(
                new Consumption(
                        dto.getName(),
                        dto.getPrice(),
                        false,
                        userService.currentUser().getId(),
                        new Date()
                )
        ));
    }

    @Override
    public ConsumptionDto edit(Long id, ConsumptionDto dto) {
        Consumption consumption = repository.getById(id);
        if (consumption == null) throw new RuntimeException("Consumption not found: " + id);

        if (consumption.getUserId().equals(userService.currentUser().getId())) {
            ConsumptionDto.toDtoHis(consumptionHistoryRepository.save(new ConsumptionHistory(
                    consumption.getName(),
                    consumption.getPrice(),
                    userService.currentUser().getId(),
                    consumption.getCreatedDate(),
                    new Date(),
                    dto.getEComment(),
                    consumption
            )));
            repository.save(consumption);
            consumption.setName(dto.getName());
            consumption.setPrice(dto.getPrice());
            consumption.setUpdatedDate(new Date());
            consumption.setEdited(true);

            repository.save(consumption);
        } else {
            return null;
        }
        return ConsumptionDto.toDto(consumption);
    }

    @Override
    public List<ConsumptionDto> findAll() {
        return repository.findAll().stream().map(ConsumptionDto::toDto).collect(Collectors.toList());

    }

    @Override
    public List<ConsumptionDto> findAllEdit() {
        return consumptionHistoryRepository.findAll().stream().map(ConsumptionDto::toDtoHis).collect(Collectors.toList());
    }

    @Override
    public List<ConsumptionDto> getMyConsumptions() {
        Long currentUserId = userService.currentUser().getId();
        return repository.findByUserId(currentUserId).stream().map(ConsumptionDto::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ConsumptionDto> getMyEditConsumptions(Long id) {
        Consumption consumption = repository.getById(id);
        if (consumption.getUserId().equals(userService.currentUser().getId())) {
            return consumptionHistoryRepository.findByConsumption_Id(id)
                    .stream().map(ConsumptionDto::toDtoHis).collect(Collectors.toList());
        }
        return null;
    }
}