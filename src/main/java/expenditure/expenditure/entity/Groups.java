package expenditure.expenditure.entity;

import com.sun.istack.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date date;
    @Nullable
    @ManyToMany(cascade = {CascadeType.ALL})
    private List<User> users;
    @OneToMany(cascade = {CascadeType.ALL})
    @Nullable
    private List<Consumption> consumption;


    public Groups(String name, Date date, List<User> users, List<Consumption> consumption) {
        this.name = name;
        this.date = date;
        this.users = users;
        this.consumption = consumption;
    }
}

