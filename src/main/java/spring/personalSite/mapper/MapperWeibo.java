package spring.personalSite.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import spring.personalSite.model.Weibo;
import spring.personalSite.model.Comment;
import spring.personalSite.model.WeiboWithComments;

import java.util.ArrayList;

@Mapper
@Repository
public interface MapperWeibo {

    ArrayList<Weibo> timelineWeibos(int userId);

    ArrayList<Comment> commentsFromWeibo(int weiboId);

    ArrayList<WeiboWithComments> currentUserWeibosWithComments(int userId);

    ArrayList<Weibo> weibosFromUserId(int userId);

    void addWeibo(String content, int userId);

    void addFollowee(int followerId, int followeeId);

    void addWeiboComment(String content, int userId, int weiboId);

    void deleteWeiboComment(int commentId);

    ArrayList<Comment> currentUserWeiboComment(int commentId, int weiboUserId, int commentUserId);

    void deleteWeiboById(int id);

    void updateWeiboById(String content, int id);

    void updateCommentById(String content, int id);

    Weibo currentUserWeiboById(int id, int userId);

    int weiboCountFromUserId(int userId);

    int followeeCountFromUserId(int userId);
}
