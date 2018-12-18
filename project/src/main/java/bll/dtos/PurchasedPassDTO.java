package bll.dtos;

public class PurchasedPassDTO {
    private int id;
    private String expirationDate;
    private PassDTO pass;
    private int userId;

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

    public PassDTO getPass() {
        return pass;
    }

    public void setPass(PassDTO pass) {
        this.pass = pass;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
