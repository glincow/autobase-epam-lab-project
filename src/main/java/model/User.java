package model;

public class User {

    private Long id;
    private String login;
    private String name;
    private String password;
    private Role role;

    public enum Role {

        ADMIN,
        MANAGER,
        DRIVER,
        CUSTOMER;

        public Long getId() {
            return (long) ordinal() + 1;
        }

    }

    public User() {}

    public User(Long id, String login, String name, String password, Role role) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Long ordinal) {
        this.role = Role.values()[ordinal.intValue() - 1];
    }

    @Override
    public String toString() {
        return "User [id = " + id + ", login = " + login + ", name = " + name +
                ", password = " + password + ", role = " + role + "]";
    }
}
