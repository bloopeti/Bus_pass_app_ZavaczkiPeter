package bll.dtos;

import java.util.List;

public class CartDTO {
    private int id;
    private int userId;
    private List<PassDTO> passes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<PassDTO> getPasses() {
        return passes;
    }

    public void setPasses(List<PassDTO> passes) {
        this.passes = passes;
    }
}
