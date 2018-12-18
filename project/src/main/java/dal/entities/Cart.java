package dal.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(optional = false)//(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany//(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable
    private List<Pass> passes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Pass> getPasses() {
        return passes;
    }

    public void setPasses(List<Pass> passes) {
        this.passes = passes;
    }
}
