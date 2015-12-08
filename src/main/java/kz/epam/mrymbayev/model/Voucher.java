package kz.epam.mrymbayev.model;

public class Voucher extends BaseEntity{

    private String type;
    private String cost;

    public Voucher() {

    }

    public Voucher(Long id, String type, String cost) {
        super(id);
        this.type = type;
        this.cost = cost;
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

    @Override
    public String toString() {
        return "Voucher id " + this.id +
                " type " + this.type +
                " cost " + this.cost;
    }
}
