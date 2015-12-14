package kz.epam.mrymbayev.model;

public class User extends BaseEntity {
    public static final User ANONYMOUS = new User(new Role("user"));

    private String login;
    private String password;
    private long voucherId;
    private Role role;

    public User(){}

    public User(Role role1) {
        this.role = role1;
    }

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

    public void setRole(Role role){
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", voucher_id='" + voucherId + '\'' +
                '}';
    }

}
