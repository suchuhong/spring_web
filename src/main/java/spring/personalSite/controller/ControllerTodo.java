package spring.personalSite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import spring.personalSite.model.Todo;
import spring.personalSite.model.User;
import spring.personalSite.service.ServiceTodo;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class ControllerTodo {

    ServiceTodo serviceTodo;
    ControllerHelper controllerHelper;

    public ControllerTodo(ServiceTodo serviceTodo, ControllerHelper controllerHelper) {
        this.serviceTodo = serviceTodo;
        this.controllerHelper = controllerHelper;
    }

    @GetMapping("/todo/all")
    public ModelAndView all(HttpServletRequest request) {
        User currentUser = this.controllerHelper.currentUser(request);
        ArrayList<Todo> todos = this.serviceTodo.currentUserTodos(currentUser);

        ModelAndView modelAndView = new ModelAndView("todo_all");
        modelAndView.addObject("todos", todos);
        return modelAndView;
    }

    @GetMapping("/todo/add")
    public ModelAndView add(String content, HttpServletRequest request) {
        User currentUser = this.controllerHelper.currentUser(request);
        // 处理数据
        this.serviceTodo.add(content, currentUser);
        // 返回数据
        return new ModelAndView("redirect:/todo/all");
    }

    @GetMapping("/todo/delete")
    public ModelAndView delete(int id, HttpServletRequest request) {

        User currentUser = this.controllerHelper.currentUser(request);
        // 处理数据
        if (this.serviceTodo.currentUserTodo(id, currentUser.id)) {
            this.serviceTodo.delete(id);
        }
        // 返回数据
        return new ModelAndView("redirect:/todo/all");
    }

    @GetMapping("/todo/edit")
    public ModelAndView edit(int id, HttpServletRequest request) {
        User currentUser = this.controllerHelper.currentUser(request);
        // 处理数据
        if (this.serviceTodo.currentUserTodo(id, currentUser.id)) {
            ModelAndView modelAndView = new ModelAndView("todo_edit");
            modelAndView.addObject("todo_id", id);
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/todo/all");
        }

    }

    @GetMapping("/todo/update")
    public ModelAndView update(int id, String content, HttpServletRequest request) {
        // 处理数据
        this.serviceTodo.update(id, content);
        // 返回数据
        return new ModelAndView("redirect:/todo/all");
    }
}
