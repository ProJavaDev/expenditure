package expenditure.expenditure.entity;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name = ("Users"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String  password;

    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable
//            (
//                    name = ("user_role"),
//                    joinColumns = {@JoinColumn(name = ("user_id"), referencedColumnName = ("id"))},
//                    inverseJoinColumns = {@JoinColumn(name = ("role_name"),referencedColumnName = ("name"))}
//            )
    private Set<Role> roles= new HashSet<>();

    public User(String username, String password, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
