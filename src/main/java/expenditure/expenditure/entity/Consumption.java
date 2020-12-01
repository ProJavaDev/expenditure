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
    private String editComment;
    private Long userId;
    private Date createdDate;
    private Date updatedDate;
    private Long groupId;

    public Consumption() {
    }

    public Consumption(String name, String price, Boolean edited, String editComment, Long userId, Date createdDate, Date updatedDate, Long groupId) {
        this.name = name;
        this.price = price;
        this.edited = edited;
        this.editComment = editComment;
        this.userId = userId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.groupId = groupId;

    }
    }
