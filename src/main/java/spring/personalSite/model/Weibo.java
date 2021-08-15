package spring.personalSite.model;

import spring.personalSite.util.Utils;

import java.util.ArrayList;

public class Weibo {
    private int id;
    private String content;
    private int userId;
    private ArrayList<Comment> oldComments;

    public Weibo(int id, String content, int userId) {
        this.id = id;
        this.content = content;
        this.userId = userId;
    }

    public Weibo() {}

    public static void log(String format, Object... args) {
        System.out.println(String.format(format, args));
    }

    @Override
    public String toString() {
        String s = String.format(
                "(id: %s, 内容: %s, user_id: %s)",
                this.getId(),
                this.getContent(),
                this.getUserId()
        );
        return s;
    }

    public int getId() {
        Utils.log("访问了 id");
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ArrayList<Comment> getOldComments() {
        return oldComments;
    }

    public void setOldComments(ArrayList<Comment> oldComments) {
        this.oldComments = oldComments;
    }
}
