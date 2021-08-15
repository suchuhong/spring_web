package spring.personalSite.service;

import org.springframework.stereotype.Component;
import spring.personalSite.model.User;
import spring.personalSite.mapper.MapperMessage;
import spring.personalSite.model.Message;

import java.util.ArrayList;

@Component
public class ServiceMessage {

    MapperMessage mapperMessage;

    ServiceMessage(MapperMessage mapperMessage) {
        this.mapperMessage = mapperMessage;
    }

    // 换web框架之后 service/服务这一块不用动
    // 用于显示所有用户的 message
    public ArrayList<Message> allMessages() {
        // 读出 message 数据
        ArrayList<Message> messages = this.mapperMessage.allMessages();
        return messages;
    }

    // 用于显示当前用户的 message
    public ArrayList<Message> currentUserMessages(User currentUser) {
        // 读出 message 数据
        ArrayList<Message> messages = this.mapperMessage.messageFromUsername(currentUser.getUsername());
        return messages;
    }

    // 添加 message
    public void add(User currentUser, String content) {
        this.mapperMessage.addMessage(currentUser.getUsername(), content);
    }

}
