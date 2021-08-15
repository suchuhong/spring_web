package spring.personalSite.controller;

import org.springframework.stereotype.Controller;
import spring.personalSite.model.User;
import spring.personalSite.service.ServiceAuth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ControllerHelper {

    ServiceAuth serviceAuth;

    ControllerHelper(ServiceAuth serviceAuth) {
        this.serviceAuth = serviceAuth;
    }

    public User currentUser(HttpServletRequest request) {
        String username;
        Cookie[] cookies = request.getCookies();
        boolean found = false;
        String session_id = "";
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("session_id")) {
                    session_id = cookie.getValue();
                    found = true;
                }
            }
        }
        if (found) {
            username = this.serviceAuth.usernameFromSessionId(session_id);
        } else {
            username = "游客";
        }

        User user = this.serviceAuth.userFromUsername(username);
        return user;
    }
}
