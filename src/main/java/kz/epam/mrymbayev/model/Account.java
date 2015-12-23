package kz.epam.mrymbayev.model;

public class Account extends BaseEntity{

    private long userId;
    private long asset;

    public Account() {
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setAsset(long asset) {
        this.asset = asset;
    }

    public long getAsset() {
        return asset;
    }
}
