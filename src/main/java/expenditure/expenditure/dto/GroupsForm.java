package expenditure.expenditure.dto;

import expenditure.expenditure.entity.Consumption;
import lombok.Data;

import java.util.List;

@Data
public class GroupsForm {

    Long id;
    String name;
    List<Long> users;
    List<Consumption> consumptions;

}
