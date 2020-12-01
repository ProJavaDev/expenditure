package expenditure.expenditure.controller;

import expenditure.expenditure.dto.GroupsDto;
import expenditure.expenditure.dto.GroupsForm;
import expenditure.expenditure.service.impl.GroupsServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/group")
public class GroupsController {

    private final GroupsServiceImpl service;

    public GroupsController(GroupsServiceImpl service) {
        this.service = service;
    }

    @PostMapping("add")
    public GroupsDto create(@RequestBody GroupsForm form) {
        return service.add(form);
    }

    @GetMapping("get")
    public List<GroupsDto> findAll(){
        return service.findAll();
    }
}
