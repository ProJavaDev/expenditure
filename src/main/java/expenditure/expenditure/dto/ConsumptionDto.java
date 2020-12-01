package expenditure.expenditure.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import expenditure.expenditure.entity.Consumption;

import expenditure.expenditure.entity.ConsumptionHistory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsumptionDto {

    private Long id;
    private String name;
    private String price;
    private Boolean edited;
    private String editComment;
    private Long userId;
    private Long createdDate;
    private Long updatedDate;
    private Long groupId;


    public static ConsumptionDto toDto(Consumption consumption) {
        ConsumptionDto consumptionDto = new ConsumptionDto(
                consumption.getId(),
                consumption.getName(),
                consumption.getPrice(),
                consumption.getEdited(),
                consumption.getEditComment(),
                consumption.getUserId(),
                (consumption.getCreatedDate()==null) ? new Date().getTime() : consumption.getCreatedDate().getTime(),
                null,
                consumption.getGroupId());
        return consumptionDto;
    }

    public static ConsumptionDto toDtoHis(ConsumptionHistory consumptionHistory) {
        ConsumptionDto consumptionDto = new ConsumptionDto(
                consumptionHistory.getId(),
                consumptionHistory.getName(),
                consumptionHistory.getPrice(),
                consumptionHistory.getEdited(),
                consumptionHistory.getEditComment(),
                consumptionHistory.getUserId(),
                consumptionHistory.getCreatedDate().getTime(),
                consumptionHistory.getUpdatedDate().getTime(),
                consumptionHistory.getGroupId()
        );
        return consumptionDto;
    }
}
