package ua.nure.chub.Elective.db.entity;

/**
 * Author Lera
 * created 01.09.2017.
 */
public class User {
    private int user_id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
    private boolean blocked;

    public User() {

    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String password, int role_id) {
        this.login = login;
        this.password = password;
        this.role = Role.getRole(role_id);
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public int getRoleId() {
        return Role.valueOf(this.role.toString()).ordinal() + 1;
    }

    public Role getRole() {
        return role;
    }

    public void setRoleId(int id) {
        role = Role.getRole(id);
    }

    @Override
    public String toString() {
        return "User [login=" + login
                + ", firstName=" + firstName
                + ", lastName=" + lastName
                + ", roleId=" + role.toString();
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
