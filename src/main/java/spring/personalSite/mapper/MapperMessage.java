package spring.personalSite.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import spring.personalSite.model.Message;

import java.util.ArrayList;


@Mapper
@Repository
public interface MapperMessage {

    ArrayList<Message> messageFromUsername(String username);

    ArrayList<Message> allMessages();

    void addMessage(String username, String content);
}
