package spring.personalSite.model;

public class User {
    public int id;
    public String username;
    public String password;
    public UserRole role;
    public String salt;
    public String avatar;

    public User(int id, String username, String password, UserRole role, String salt, String avatar) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.salt = salt;
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        String s = String.format(
                "(用户名: %s, 密码: %s, 角色 %s 盐 %s)",
                this.username,
                this.password,
                this.role,
                this.salt
        );
        return s;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
