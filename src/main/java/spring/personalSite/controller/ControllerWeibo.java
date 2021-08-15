package spring.personalSite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import spring.personalSite.service.ServiceAuth;
import spring.personalSite.model.Weibo;
import spring.personalSite.model.User;
import spring.personalSite.service.ServiceWeibo;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;


@Controller
public class ControllerWeibo {

    ServiceWeibo serviceWeibo;
    ServiceAuth serviceAuth;
    ControllerHelper controllerHelper;

    ControllerWeibo(ServiceWeibo serviceWeibo, ServiceAuth serviceAuth, ControllerHelper controllerHelper) {
        this.serviceWeibo = serviceWeibo;
        this.serviceAuth = serviceAuth;
        this.controllerHelper = controllerHelper;
    }

    @GetMapping("/weibo/timeline")
    public ModelAndView timeline(HttpServletRequest request) {
        User currentUser = this.controllerHelper.currentUser(request);
        ArrayList<Weibo> weibos = this.serviceWeibo.timelineWeibos(currentUser);
        HashMap<Integer, User> allFollowee = this.serviceWeibo.usersFromFollowee(weibos);
        // 读出 模板 文件并把数据放进 html 文件
        ModelAndView modelAndView = new ModelAndView("weibo_timeline_new");
        modelAndView.addObject("weibos", weibos);
        modelAndView.addObject("all_followee", allFollowee);
        return modelAndView;
    }

    @GetMapping("/weibo/all")
    public ModelAndView all(Integer user_id, HttpServletRequest request) {

        // 显示所有 weibo
        User currentUser;
        // 当用户访问 /weibo/all?user_id=1 的时候，能访问用户 id 为 1 的所有微博。
        // 当用户访问 /weibo/all 的时候，能访问当前登陆用户的所有微博。
        if (user_id != null) {
            currentUser = this.serviceAuth.userFromId(user_id);
        } else {
            currentUser = this.controllerHelper.currentUser(request);
        }
        //        ArrayList<WeiboWithComments> weibos = this.serviceWeibo.currentUserWeibos(currentUser);
        String avatar = currentUser.getAvatar();
        if (avatar == null) {
            avatar = "default.jpg";
        }
        ArrayList<Weibo> weibos = this.serviceWeibo.currentUserWeibos(currentUser);
        HashMap<Integer, User> allCommentUsers = this.serviceWeibo.usersFromComments(currentUser);

        ModelAndView modelAndView = new ModelAndView("weibo_all_new");
        modelAndView.addObject("current_user", currentUser);// 增加
        modelAndView.addObject("avatar", avatar);// 增加
        modelAndView.addObject("all_comment_users", allCommentUsers);// 增加
        modelAndView.addObject("weibos", weibos);
        return modelAndView;
    }


    @GetMapping("/weibo/add")
    public ModelAndView add(String content, HttpServletRequest request) {

        if (!content.isEmpty()) {
            User currentUser = this.controllerHelper.currentUser(request);
            // 处理数据
            this.serviceWeibo.add(content, currentUser);
        }
        // 返回数据
        return new ModelAndView("redirect:/weibo/all");
    }

    @GetMapping("/weibo/follow")
    public ModelAndView follow(int user_id, HttpServletRequest request) {

        User currentUser = this.controllerHelper.currentUser(request);
        // 避免自己关注自己
        if (currentUser.getId() != user_id) {
            // 处理数据
            this.serviceWeibo.follow(currentUser, user_id);
        }
        return new ModelAndView("redirect:/weibo/all");
    }

    @GetMapping("/comment/new")
    public static ModelAndView commentNew(int id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("comment_new");
        modelAndView.addObject("weibo_id", id);
        return modelAndView;
    }

    @GetMapping("/comment/add")
    public ModelAndView commentAdd(String content, int id, HttpServletRequest request) {

        if (!content.isEmpty()) {
            int weiboId = id;
            User currentUser = this.controllerHelper.currentUser(request);
            // 处理数据
            this.serviceWeibo.commentAdd(content, currentUser, weiboId);
        }
        // 返回数据
        return new ModelAndView("redirect:/weibo/all");
    }

    @GetMapping("/comment/delete")
    public ModelAndView commentDelete(int id, HttpServletRequest request) {

        User currentUser = this.controllerHelper.currentUser(request);
        // 处理数据
        // 权限验证
        if (this.serviceWeibo.currentUserWeiboComment(id, currentUser.getId())) {
            this.serviceWeibo.commentDelete(id);
        }
        // 返回数据
        return new ModelAndView("redirect:/weibo/all");
    }

    @GetMapping("/weibo/delete")
    public ModelAndView delete(int id, HttpServletRequest request) {

        User currentUser = this.controllerHelper.currentUser(request);
        // 处理数据
        if (this.serviceWeibo.currentUserWeibo(id, currentUser.id)) {
            this.serviceWeibo.delete(id);
        }
        // 返回数据
        return new ModelAndView("redirect:/weibo/all");
    }

    @GetMapping("/weibo/edit")
    public ModelAndView edit(int id, HttpServletRequest request) {

        User currentUser = this.controllerHelper.currentUser(request);
        if (this.serviceWeibo.currentUserWeibo(id, currentUser.id)) {
            ModelAndView modelAndView = new ModelAndView("weibo_edit");
            modelAndView.addObject("weibo_id", id);
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/weibo/all");
        }
    }

    @GetMapping("/weibo/update")
    public ModelAndView update(int id, String content, HttpServletRequest request) {

        User currentUser = this.controllerHelper.currentUser(request);
        if (this.serviceWeibo.currentUserWeibo(id, currentUser.id)) {
            // 处理数据
            this.serviceWeibo.update(id, content);
        }
        // 返回数据
        return new ModelAndView("redirect:/weibo/all");
    }

    @GetMapping("/comment/edit")
    public static ModelAndView commentEdit(int id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("comment_edit");
        modelAndView.addObject("comment_id", id);
        return modelAndView;
    }

    @GetMapping("/comment/update")
    public ModelAndView commentUpdate(int id, String content, HttpServletRequest request) {
        User currentUser = this.controllerHelper.currentUser(request);
        // 处理数据
        // 权限验证
        if (this.serviceWeibo.currentUserWeiboComment(id, currentUser.getId())) {
            this.serviceWeibo.commentUpdate(id, content);
        }
        // 返回数据
        return new ModelAndView("redirect:/weibo/all");
    }

}
