package spring.personalSite.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import spring.personalSite.model.User;
import spring.personalSite.model.Weibo;
import spring.personalSite.service.ServiceAuth;
import spring.personalSite.model.UserRole;
import spring.personalSite.service.ServiceWeibo;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class ControllerPublic {

    ServiceAuth serviceAuth;
    ControllerHelper controllerHelper;
    ServiceWeibo serviceWeibo;

    ControllerPublic(ServiceAuth serviceAuth, ControllerHelper controllerHelper, ServiceWeibo serviceWeibo) {
        this.serviceAuth = serviceAuth;
        this.controllerHelper = controllerHelper;
        this.serviceWeibo = serviceWeibo;
    }

    @GetMapping("/")
    public ModelAndView route() {
        return new ModelAndView("redirect:/index");
    }

    @GetMapping("/index")
    public ModelAndView routeIndex(HttpServletRequest request) {
        String message;
        User user = this.controllerHelper.currentUser(request);
        String username = user.username;
        message = String.format("%s", username);

        // 显示头像，如果没有显示默认图片
        String avatar = user.getAvatar();
        if (avatar == null) {
            avatar = "default.jpg";
        }

        ArrayList<Weibo> weibos = this.serviceWeibo.currentUserWeibos(user);
        int weiboCount = this.serviceWeibo.weiboCount(user.getId());
        int followeeCount = this.serviceWeibo.followeeCount(user.getId());
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("message", message);
        modelAndView.addObject("weibos", weibos);
        modelAndView.addObject("weibo_count", weiboCount);
        modelAndView.addObject("followee_count", followeeCount);
        modelAndView.addObject("avatar", avatar);
        return modelAndView;
    }

    @GetMapping("/profile")
    public ModelAndView routeProfile(HttpServletRequest request) {
        User user = this.controllerHelper.currentUser(request);

        if (user.username.equals("游客")) {
            return new ModelAndView("redirect:/login/view");
        }

        ModelAndView modelAndView = new ModelAndView("profile_card");
        modelAndView.addObject("user", user);
        if (user.getAvatar() == null) {
            user.setAvatar("default.jpg");
        }
        return modelAndView;
    }

    @GetMapping("/admin")
    public ModelAndView routeAdmin(HttpServletRequest request) {
        String message;
        User user = this.controllerHelper.currentUser(request);
        String username = user.username;

        if (user.role == UserRole.admin) {
            message = String.format("当前登录用户 %s", username);
        } else {
            message = "没有权限访问 " + username;
        }
        return new ModelAndView("redirect:/index");
    }

}

