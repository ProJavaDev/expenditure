package expenditure.expenditure.dto;

import com.sun.istack.Nullable;
import expenditure.expenditure.entity.Groups;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupsDto {

    private Long id;
    private String name;
    private Long date;
    @Nullable
    private List<UserDto> users;
    @Nullable
    private List<ConsumptionDto> consumption;

    public static GroupsDto toDto(Groups groups) {
        GroupsDto groupsDto = new GroupsDto(
                groups.getId(),
                groups.getName(),
                groups.getDate().getTime(),
                groups.getUsers().stream().map(UserDto::toDto).collect(Collectors.toList()),
                (groups.getConsumption()==null) ? null:groups.getConsumption().stream().map(ConsumptionDto::toDto).collect(Collectors.toList())
        );
        return groupsDto;
    }
}
