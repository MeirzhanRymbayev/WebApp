package kz.epam.mrymbayev.model;

public class Customer extends BaseEntity {
    private String login;
    private String password;
    private long voucherID;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getVoucherID() {
        return voucherID;
    }

    public void setVoucherID(long voucherID) {
        this.voucherID = voucherID;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
