package expenditure.expenditure.service;

import expenditure.expenditure.dto.GroupsDto;
import expenditure.expenditure.dto.GroupsForm;
import expenditure.expenditure.entity.Groups;

import java.util.List;

public interface GroupsService {

    GroupsDto add(GroupsForm form);

    List<GroupsDto> findAll();
}
