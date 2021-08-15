package spring.personalSite.service;

import org.springframework.stereotype.Component;
import spring.personalSite.mapper.MapperTodo;
import spring.personalSite.model.User;
import spring.personalSite.model.Todo;

import java.util.ArrayList;

@Component
public class ServiceTodo {

    MapperTodo mapperTodo;

    ServiceTodo(MapperTodo mapperTodo) {
        this.mapperTodo = mapperTodo;
    }

    public boolean currentUserTodo(int todoId, int currentUserId) {

        Todo todo = this.mapperTodo.currentUserTodo(todoId, currentUserId);
        boolean result = todo != null;
        return result;
    }

    // 换web框架之后 service/服务这一块不用动
    public ArrayList<Todo> currentUserTodos(User currentUser) {

        ArrayList<Todo> todos = this.mapperTodo.currentUserTodos(currentUser.getId());
        return todos;
    }

    public void add(String content, User currentUser) {
        this.mapperTodo.addTodo(content, currentUser.getId());
    }

    public void delete(int id) {
        this.mapperTodo.deleteTodoById(id);
    }

    public void update(int id, String content) {
        this.mapperTodo.updateTodoById(id, content);
    }
}
