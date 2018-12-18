package dal.entities;

import javax.persistence.*;

@Entity
@Table
public class PurchasedPass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String expirationDate;
    @ManyToOne//(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(referencedColumnName = "id")
    private Pass pass;
    @ManyToOne//(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(referencedColumnName = "id")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Pass getPass() {
        return pass;
    }

    public void setPass(Pass pass) {
        this.pass = pass;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
