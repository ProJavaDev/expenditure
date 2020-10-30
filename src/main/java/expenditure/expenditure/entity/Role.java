package expenditure.expenditure.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
@Data
@Entity
public class Role {

    @Id
//    Long id;
    private String name;
}
