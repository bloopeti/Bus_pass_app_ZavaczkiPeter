package dal.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column
    private String emailAddress;
    @Column
    private String password;
    @Column
    private int isAdmin;
    @OneToMany(mappedBy = "user")
    private List<PurchasedPass> purchasedPasses;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private Cart cart;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public List<PurchasedPass> getPurchasedPasses() {
        return purchasedPasses;
    }

    public void setPurchasedPasses(List<PurchasedPass> purchasedPasses) {
        this.purchasedPasses = purchasedPasses;
    }
}
