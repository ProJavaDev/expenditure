package expenditure.expenditure.service.impl;

import expenditure.expenditure.dto.GroupsDto;
import expenditure.expenditure.dto.GroupsForm;
import expenditure.expenditure.entity.Consumption;
import expenditure.expenditure.entity.Groups;
import expenditure.expenditure.entity.User;
import expenditure.expenditure.repository.ConsumptionRepository;
import expenditure.expenditure.repository.GroupsRepository;
import expenditure.expenditure.repository.UserRepository;
import expenditure.expenditure.service.ConsumptionService;
import expenditure.expenditure.service.GroupsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupsServiceImpl implements GroupsService {
    private final GroupsRepository repository;
    private final UserRepository userRepository;


    public GroupsServiceImpl(GroupsRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;

    }

    @Override
    public GroupsDto add(GroupsForm form) {

//        List<User> u =new ArrayList<>();
//        for (Long userId:form.getUsers()) {
//            u.add(userRepository.getOne(userId));
//        }

        List<User> users = new ArrayList<>();
        if (form.getUsers() != null) {
            users = form.getUsers().stream().map(userRepository::getOne).collect(Collectors.toList());
        }

        return GroupsDto.toDto(repository.save(
                new Groups(
                        form.getName(),
                        new Date(),
                        users,
                        form.getConsumptions()
                )
        ));
    }

    @Override
    public List<GroupsDto> findAll() {
        return repository.findAll().stream().map(GroupsDto::toDto).collect(Collectors.toList());
    }
}

