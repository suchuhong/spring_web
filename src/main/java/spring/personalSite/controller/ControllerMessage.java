package spring.personalSite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import spring.personalSite.model.User;
import spring.personalSite.service.ServiceAuth;
import spring.personalSite.service.ServiceMessage;
import spring.personalSite.model.Message;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class ControllerMessage {

    ServiceAuth serviceAuth;
    ServiceMessage serviceMessage;
    ControllerHelper controllerHelper;

    public ControllerMessage(ServiceAuth serviceAuth, ControllerHelper controllerHelper, ServiceMessage serviceMessage) {
        this.serviceAuth = serviceAuth;
        this.controllerHelper = controllerHelper;
        this.serviceMessage = serviceMessage;
    }

    @GetMapping("/message/all")
    public ModelAndView all(HttpServletRequest request) {

        // 显示当前用户的 message
        User currentUser = this.controllerHelper.currentUser(request);
        ArrayList<Message> messages = this.serviceMessage.currentUserMessages(currentUser);
        // 显示所有用户的 message
        //        ArrayList<Message> messages = ServiceMessage.allMessages(this.mapperMessage);
        ModelAndView modelAndView = new ModelAndView("message_all");
        modelAndView.addObject("messages", messages);
        return modelAndView;
    }

    @GetMapping("/message/add/get")
    public String addGet(String content, HttpServletRequest request) {

        User currentUser = this.controllerHelper.currentUser(request);
        if (!content.isEmpty()) {
            this.serviceMessage.add(currentUser, content);
        }
        return "redirect:/message/all";
    }

    @PostMapping("/message/add/post")
    public String addPost(String content, HttpServletRequest request) {

        User currentUser = this.controllerHelper.currentUser(request);
        if (!content.isEmpty()) {
            this.serviceMessage.add(currentUser, content);
        }
        return "redirect:/message/all";
    }

}
