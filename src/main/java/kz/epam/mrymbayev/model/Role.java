package kz.epam.mrymbayev.model;

public class Role {

    private String role;

    public Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }

    @Override
    public int hashCode() {
        return role.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Role)) return false;
        if(obj == null) return false;
        Role o = (Role) obj;
        return role.equals(o.getRole());
    }
}
