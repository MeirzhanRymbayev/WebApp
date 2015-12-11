package kz.epam.mrymbayev.model;

public class TourAgent extends BaseEntity {

    private String login;
    private String password;
    private long voucherId;
    private Role role;

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

    public long getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(long voucherId) {
        this.voucherId = voucherId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
