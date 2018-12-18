package bll.dtos;

import java.util.List;

public class UserDTO {
    private int id;
    private String emailAddress;
    private String password;
    private int isAdmin;
    private List<PurchasedPassDTO> purchasedPasses;
    private CartDTO cart;

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

    public List<PurchasedPassDTO> getPurchasedPasses() {
        return purchasedPasses;
    }

    public void setPurchasedPasses(List<PurchasedPassDTO> purchasedPasses) {
        this.purchasedPasses = purchasedPasses;
    }

    public CartDTO getCart() {
        return cart;
    }

    public void setCart(CartDTO cart) {
        this.cart = cart;
    }
}
