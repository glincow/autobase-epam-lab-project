package model;

public class User {

    private int id;
    private String login;
    private String name;
    private String password;
    private int role;

    public User(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User [id = " + id + ", login = " + login + ", name = " + name +
                ", password = " + password + ", role = " + role + "]";
    }
}
