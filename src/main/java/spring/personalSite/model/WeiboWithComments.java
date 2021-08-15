package spring.personalSite.model;

import java.util.ArrayList;

public class WeiboWithComments extends Weibo {

    private ArrayList<Comment> comments;

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
