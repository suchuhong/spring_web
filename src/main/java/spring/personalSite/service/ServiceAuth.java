package spring.personalSite.service;

import org.springframework.stereotype.Component;
import spring.personalSite.mapper.MapperAuth;
import spring.personalSite.model.User;
import spring.personalSite.model.UserRole;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.UUID;

@Component
public class ServiceAuth {

    MapperAuth mapperAuth;

    ServiceAuth(MapperAuth mapperAuth) {
        this.mapperAuth = mapperAuth;
    }

    public static String hexFromBytes(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (int i = 0, bytesLength = bytes.length; i < bytesLength; i++) {
            byte currentByte = bytes[i];
            // 02 代表不足两位补足两位 x代表用16进制表示
            // String.format("%02x", 0) = "00"
            result.append(String.format("%02x", currentByte));
        }
        return result.toString();
    }

    public static String SaltedPasswordHash(String password, String salt) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        String salted = salt + password;
        md.update(salted.getBytes(StandardCharsets.UTF_8));
        byte[] result = md.digest();
        return  hexFromBytes(result);
    }

    public String login(String username, String password) {

        User currentUser = this.mapperAuth.userFromUsername(username);
        if (currentUser == null) {
            return "";
        }
        String salt = currentUser.salt;
        String saltedPassword = ServiceAuth.SaltedPasswordHash(password, salt);

        ArrayList<User> users = this.mapperAuth.userFromUsernameAndPassword(username, saltedPassword);
        if (users.size() > 0) {
            String sessionId = UUID.randomUUID().toString();
            this.mapperAuth.addSessionId(username, sessionId);
            return sessionId;
        } else {
            return "";
        }
    }

    public static User guest() {
        User user = new User(0, "游客", "dadasds", UserRole.guest, "dasdas", null);
        return user;
    }

    public String usernameFromSessionId(String sessionId) {

        ArrayList<User> users = this.mapperAuth.usernameFromSessionId(sessionId);

        if (users.size() > 0) {
            User user = users.get(0);
            return user.getUsername();
        } else {
            return guest().getUsername();
        }
    }

    public User userFromUsername(String username) {
        User user = this.mapperAuth.userFromUsername(username);
        if (user != null) {
            return user;
        } else {
            return guest();
        }
    }

    public User userFromId(int id) {

        User user = this.mapperAuth.userFromId(id);
        if (user != null) {
            return user;
        } else {
            return guest();
        }

    }

    public ArrayList<User> allUsers() {

        ArrayList<User> users = this.mapperAuth.allUsers();
        return users;

    }

    public void update(String username, String newPassword) {

        User user = this.mapperAuth.userFromUsername(username);
        String saltedPassword = ServiceAuth.SaltedPasswordHash(newPassword, user.getSalt());
        this.mapperAuth.updateUserInfo(username, saltedPassword);
    }

    public boolean register(String username, String password) {
        String salt = UUID.randomUUID().toString();
        String saltedPassword = ServiceAuth.SaltedPasswordHash(password, salt);

        this.mapperAuth.addUser(username, saltedPassword, UserRole.normal.toString(), salt);

        User user = userFromUsername(username);
        boolean result = user != null;
        return result;

    }

    public void updateAvatar(String avatarName, String username) {
        this.mapperAuth.setAvatar(avatarName, username);
    }
}
