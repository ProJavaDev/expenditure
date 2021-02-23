package expenditure.expenditure.service.impl;

import expenditure.expenditure.dto.ConsumptionDto;
import expenditure.expenditure.entity.Consumption;
import expenditure.expenditure.entity.ConsumptionHistory;
import expenditure.expenditure.exception.ConsumptionNotFoundException;
import expenditure.expenditure.exception.UserRoleNotFoundException;
import expenditure.expenditure.exception.YourConsumptionNotFoundException;
import expenditure.expenditure.repository.ConsumptionHistoryRepository;
import expenditure.expenditure.repository.ConsumptionRepository;
import expenditure.expenditure.repository.UserRepository;
import expenditure.expenditure.service.ConsumptionService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ConsumptionServiceImpl implements ConsumptionService {

    private final ConsumptionRepository repository;
    private final UserServiceImpl userServiceImpl;
    private final ConsumptionHistoryRepository consumptionHistoryRepository;
    private final UserRepository userRepository;

    public ConsumptionServiceImpl(ConsumptionRepository consumptionRepository, UserServiceImpl userServiceImpl,
                                  ConsumptionHistoryRepository consumptionHistoryRepository, UserRepository userRepository) {
        this.repository = consumptionRepository;
        this.userServiceImpl = userServiceImpl;
        this.consumptionHistoryRepository = consumptionHistoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ConsumptionDto add(ConsumptionDto dto) {
        return ConsumptionDto.toDto(repository.save(
                new Consumption(
                        dto.getName(),
                        dto.getPrice(),
                        false,
                        dto.getEditComment(),
                        userServiceImpl.currentUser().getId(),
                        new Date(),
                        null,
                        dto.getGroupId()
                )
        ));
    }

    @Override
    public ConsumptionDto edit(Long id, ConsumptionDto dto) {
        Consumption consumption = repository.getById(id);
        if (consumption == null) throw new ConsumptionNotFoundException("Consumption not found: " + id);

        if (consumption.getUserId().equals(userServiceImpl.currentUser().getId())) {
            ConsumptionDto.toDtoHis(consumptionHistoryRepository.save(new ConsumptionHistory(
                    consumption.getName(),
                    consumption.getPrice(),
                    true,
                    userServiceImpl.currentUser().getId(),
                    consumption.getCreatedDate(),
                    new Date(),
                    dto.getEditComment(),
                    consumption,
                    dto.getGroupId()
            )));
            repository.save(consumption);
            consumption.setName(dto.getName());
            consumption.setPrice(dto.getPrice());
            consumption.setEditComment(dto.getEditComment());
            consumption.setUpdatedDate(new Date());
            consumption.setEdited(true);

            repository.save(consumption);

        } else {
            throw new YourConsumptionNotFoundException(" You only edited Consumption yourself! ");
        }
        return ConsumptionDto.toDto(consumption);
    }

    @Override
    public List<ConsumptionDto> findAll() {
        String user = userServiceImpl.currentUser().getRoles().toString();
        if (user.equals("[Role(name=ROLE_ADMIN)]")) {
            return repository.findAll().stream().map(ConsumptionDto::toDto).collect(Collectors.toList());
        } else {
            throw new UserRoleNotFoundException("You are restricted from using this request!");
        }
    }

    @Override
    public List<ConsumptionDto> findAllEdit() {
        String user = userServiceImpl.currentUser().getRoles().toString();
        if (user.equals("[Role(name=ROLE_ADMIN)]")) {
            return consumptionHistoryRepository.findAll()
                    .stream().map(ConsumptionDto::toDtoHis).collect(Collectors.toList());
        } else {
            throw new UserRoleNotFoundException("You are restricted from using this request!");
        }
    }

    @Override
    public List<ConsumptionDto> getMyConsumptions() {
        Long currentUserId = userServiceImpl.currentUser().getId();
        return repository.findByUserId(currentUserId)
                .stream().map(ConsumptionDto::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ConsumptionDto> getConsumptionHistoryById(Long id) {
        Consumption consumption = repository.getById(id);
        if (consumption != null && consumption.getUserId().equals(userServiceImpl.currentUser().getId()) && consumption.getEdited()) {
            return consumptionHistoryRepository.findByConsumption_Id(id)
                    .stream().map(ConsumptionDto::toDtoHis).collect(Collectors.toList());
        }
        throw new ConsumptionNotFoundException("Consumption not found: " + id);
    }

    @Override
    public List<ConsumptionDto> getConsumptionHistoryByDate(Long date, Long date2) {

        Date date1 = new Date(date);
        Date date3 = new Date(date2);

        return consumptionHistoryRepository.findByCreatedDateGreaterThanAndCreatedDateLessThanAndUserId(date1, date3, userServiceImpl.currentUser().getId())
                .stream().map(ConsumptionDto::toDtoHis).collect(Collectors.toList());
    }

    @Override
    public List<ConsumptionDto> getConsumptionByDate(Long date, Long date2) {

        Date date1 = new Date(date);
        Date date3 = new Date(date2);

        return repository.findByCreatedDateGreaterThanAndCreatedDateLessThanAndUserId(date1, date3, userServiceImpl.currentUser().getId())
                .stream().map(ConsumptionDto::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ConsumptionDto> getConsumptionByGroupId(Long id) {
        return repository.findByGroupId(id).stream().map(ConsumptionDto::toDto).collect(Collectors.toList());
    }
}