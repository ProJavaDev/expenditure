package expenditure.expenditure.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import expenditure.expenditure.entity.Consumption;

import expenditure.expenditure.entity.ConsumptionHistory;
import lombok.Data;


import java.util.Date;

@Data

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsumptionDto {

    private Long id;
    private String name;
    private String price;
    private Boolean edited;
    private String eComment;
    private Long userId;
    private Date createdDate;
    private Date updatedDate;

    public ConsumptionDto(Long id, String name, String price, Long userId, Date createdDate, Date updatedDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.edited = edited;
        this.eComment = eComment;
        this.userId = userId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public static ConsumptionDto toDto(Consumption consumption) {
        ConsumptionDto consumptionDto = new ConsumptionDto(
                consumption.getId(),
                consumption.getName(),
                consumption.getPrice(),
                consumption.getUserId(),
                consumption.getCreatedDate(),
                consumption.getUpdatedDate()


        );
        return consumptionDto;
    }

    public static ConsumptionDto toDtoHis(ConsumptionHistory consumptionHistory) {
        ConsumptionDto consumptionDto = new ConsumptionDto(
                consumptionHistory.getId(),
                consumptionHistory.getName(),
                consumptionHistory.getPrice(),
                consumptionHistory.getUserId(),
                consumptionHistory.getCreatedDate(),
                consumptionHistory.getUpdatedDate()
        );
        return consumptionDto;
    }
}
