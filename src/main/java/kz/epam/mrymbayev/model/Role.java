package kz.epam.mrymbayev.model;

public class Role {

    private String name;

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Role)) return false;
        if(obj == null) return false;
        Role o = (Role) obj;
        return name.equals(o.getName());
    }
}
