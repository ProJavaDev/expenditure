package expenditure.expenditure.controller;

import expenditure.expenditure.dto.ConsumptionDto;
import expenditure.expenditure.service.ConsumptionService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/spend")
public class ConsumptionController {

    private final ConsumptionService service;

    public ConsumptionController(ConsumptionService consumptionService) {
        this.service = consumptionService;
    }

    @PostMapping("add")
    public ConsumptionDto add(@RequestBody ConsumptionDto dto) {
        return service.add(dto);
    }

    @PutMapping("{id}")
    public ConsumptionDto update(@PathVariable Long id, @RequestBody ConsumptionDto dto) {
        return service.edit(id, dto);
    }

    @GetMapping("all")
    public List<ConsumptionDto> getUserId() {
        return service.getMyConsumptions();
    }

    @GetMapping("get-all")
    public List<ConsumptionDto> getAll()  {
        return service.findAll();
    }

    @GetMapping("edit-all")
    public List<ConsumptionDto> getAllEdit() {
        return service.findAllEdit();
    }

    @GetMapping("{id}")
    public List<ConsumptionDto> getAllEdit(@PathVariable Long id) {
        return service.getConsumptionHistoryById(id);
    }

    @GetMapping("date/history")
    public List<ConsumptionDto> getConsumptionHistoryByDate(@RequestParam Long date, Long date2){
        return service.getConsumptionHistoryByDate(date,date2);
    }
    @GetMapping("date")
    public List<ConsumptionDto> getConsumptionByDate(@RequestParam Long date, Long date2){
        return service.getConsumptionByDate(date,date2);
    }

    @GetMapping("group/{id}")
    public  List<ConsumptionDto> getConsumptionByGroupId(@PathVariable Long id){
        return service.getConsumptionByGroupId(id);
    }
}