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
    private Boolean edited;
    private Long userId;
    private Date createdDate;
    private Date updatedDate;
    private String editComment;
    private Long groupId;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Consumption consumption;


    public ConsumptionHistory(String name, String price, Boolean edited, Long userId, Date createdDate, Date updatedDate, String editComment, Consumption consumption, Long groupId) {
        this.name = name;
        this.price = price;
        this.edited = edited;
        this.userId = userId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.editComment = editComment;
        this.consumption = consumption;
        this.groupId =groupId ;
    }
}
