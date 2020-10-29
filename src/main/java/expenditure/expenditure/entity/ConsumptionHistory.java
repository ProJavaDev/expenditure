package expenditure.expenditure.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;



@NoArgsConstructor
@Data
@Entity
public class ConsumptionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String price;
    private Long userId;
    private Date createdDate;
    private Date updatedDate;
    private String eComment;
    @ManyToOne
    private Consumption consumption;


    public ConsumptionHistory(String name, String price, Long userId, Date createdDate, Date updatedDate,String eComment, Consumption consumption) {
        this.name = name;
        this.price = price;
        this.userId = userId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.eComment = eComment;
        this.consumption = consumption;
    }
}
