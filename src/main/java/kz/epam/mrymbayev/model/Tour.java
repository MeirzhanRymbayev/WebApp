package kz.epam.mrymbayev.model;

public class Tour {

    private long id;
    private String type;
    private String cost;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCost() {
        return cost;
    }


}
