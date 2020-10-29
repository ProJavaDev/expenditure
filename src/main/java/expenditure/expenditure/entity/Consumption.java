package expenditure.expenditure.entity;


import lombok.Data;


import javax.persistence.*;
import java.util.Date;



@Data
@Entity
public class Consumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String price;
    private Boolean edited;
//    private String eComment;
    private Long userId;
    private Date createdDate;
    private Date updatedDate;

    public Consumption() {
    }

    public Consumption(String name, String price, Boolean edited, Long userId, Date createdDate) {
        this.name = name;
        this.price = price;
        this.edited = edited;
//        this.eComment = eComment;
        this.userId = userId;
        this.createdDate = createdDate;

    }
}
