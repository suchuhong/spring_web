package spring.personalSite.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import spring.personalSite.model.Todo;

import java.util.ArrayList;

@Mapper
@Repository
public interface MapperTodo {

    ArrayList<Todo> currentUserTodos(int userId);

    void addTodo(String content, int userId);

    Todo currentUserTodo(int todoId, int userId);

    void deleteTodoById(int id);

    void updateTodoById(int id, String content);
}
