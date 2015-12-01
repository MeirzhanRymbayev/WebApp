package kz.epam.mrymbayev.model;

public class Voucher {

    private long id;
    private String type;
    private String cost;

    public Voucher() {

    }

    public Voucher(long id, String type, String cost) {
        this.id = id;
        this.type = type;
        this.cost = cost;
    }

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
