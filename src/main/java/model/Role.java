package model;

@Deprecated
public class Role { //use only for test, now role is joined to user

    private int id;
    private String name;

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Role [id = " + id + ", name = " + name + "]";
    }
}
