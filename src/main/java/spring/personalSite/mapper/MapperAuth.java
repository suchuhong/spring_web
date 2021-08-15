package spring.personalSite.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import spring.personalSite.model.User;

import java.util.ArrayList;

@Mapper
@Repository
public interface MapperAuth {

    ArrayList<User> allUsers();

    User userFromUsername(String username);

    ArrayList<User> userFromUsernameAndPassword(String username, String password);

    void addSessionId(String username, String sessionId);

    ArrayList<User> usernameFromSessionId(String sessionId);

    void addUser(String username, String saltedPassword, String userRole, String salt);

    void updateUserInfo(String username, String password);

    User userFromId(int id);

    void setAvatar(String avatarName, String username);

}
