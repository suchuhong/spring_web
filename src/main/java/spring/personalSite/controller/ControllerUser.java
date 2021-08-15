package spring.personalSite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import spring.personalSite.model.User;
import spring.personalSite.service.ServiceAuth;
import spring.personalSite.model.UserRole;
import spring.personalSite.util.Utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


@Controller
public class ControllerUser {

    ServiceAuth serviceAuth;
    ControllerHelper controllerHelper;

    ControllerUser(ServiceAuth serviceAuth, ControllerHelper controllerHelper) {
        this.serviceAuth = serviceAuth;
        this.controllerHelper = controllerHelper;
    }

    @GetMapping("/login/view")
    public ModelAndView loginView(HttpServletRequest request) {
        User user = this.controllerHelper.currentUser(request);
        String username = user.username;
        String message = String.format("当前登录用户 %s", username);
        ModelAndView modelAndView = new ModelAndView("login_new");
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @PostMapping("/login")
    public String login(String username, String password, HttpServletResponse response) {
        String sessionId = this.serviceAuth.login(username, password);
        Cookie cookie;
        if (sessionId.length() > 0) {
            cookie = new Cookie("session_id", sessionId);
            response.addCookie(cookie);
        }

        return "redirect:/login/view";
    }

    // 修改密码
    @GetMapping("/admin/users")
    public ModelAndView allByAdmin(HttpServletRequest request) {
        // 权限验证
        User currentUser = this.controllerHelper.currentUser(request);

        if (currentUser.role != UserRole.admin) {
            return new ModelAndView("redirect:/login/view");
        } else {
            // 读出 User 数据
            ArrayList<User> users = this.serviceAuth.allUsers();

            ModelAndView modelAndView = new ModelAndView("admin");
            modelAndView.addObject("users", users);

            return modelAndView;
        }

    }

    @PostMapping("/admin/user/update")
    public String update(String username, String password) {
        // 处理数据
        this.serviceAuth.update(username, password);
        return "redirect:/admin/users";
    }

    @GetMapping("/register/view")
    public static ModelAndView registerView() {
        ModelAndView modelAndView = new ModelAndView("register_new");
        modelAndView.addObject("message", "请注册");
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(String username, String password) {
        String message;
        if (username.isEmpty() || password.isEmpty()) {
            return new ModelAndView("redirect:/register/view");
        }
        boolean success = this.serviceAuth.register(username, password);
        if (success) {
            message = String.format("注册成功 %s", username);
        } else {
            message = "注册失败";
        }
        ModelAndView modelAndView = new ModelAndView("register_new");
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @PostMapping("/avatar/add")
    public ModelAndView uploadAvatar(MultipartFile file, HttpServletRequest request) {
        Utils.log("uploadAvatar");
        Utils.log("filename %s size %s", file.getOriginalFilename(), file.getSize());
        User currentUser = this.controllerHelper.currentUser(request);
        String avatarName = currentUser.username + '-' + file.getOriginalFilename();
        Path path = Path.of("avatar", avatarName);
//        Path path = Path.of("/root/spring_web/avatar", avatarName);
        try {
            Files.write(path, file.getBytes());
            this.serviceAuth.updateAvatar(avatarName, currentUser.getUsername());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ModelAndView("redirect:/profile");
    }

    @GetMapping("/avatar")
    public void setAvatar(String file, HttpServletResponse response) throws IOException {
        Utils.log("filename %s ", file);
        byte[] bytes = Files.readAllBytes(Path.of("avatar", file));
//        byte[] bytes = Files.readAllBytes(Path.of("/root/spring_web/avatar", file));
        response.setContentType("image/jpeg");
        response.setContentType("multipart/form-data");
        OutputStream out = response.getOutputStream();
        out.write(bytes);
        out.flush();
        //关闭响应输出流
        out.close();
    }

}
