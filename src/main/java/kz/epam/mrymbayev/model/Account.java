package kz.epam.mrymbayev.model;

public class Account extends BaseEntity{

    private long userId;
    private int asset;

    public Account() {
    }

    public Account(long id, long tourAgency, int asset) {
        super(id);
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setAsset(int asset) {
        this.asset = asset;
    }

    public int getAsset() {
        return asset;
    }
}
