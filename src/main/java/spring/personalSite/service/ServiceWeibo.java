package spring.personalSite.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import spring.personalSite.mapper.MapperAuth;
import spring.personalSite.model.User;
import spring.personalSite.model.Weibo;
import spring.personalSite.model.Comment;
import spring.personalSite.mapper.MapperWeibo;
import spring.personalSite.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class ServiceWeibo {

    MapperWeibo mapperWeibo;
    MapperAuth mapperAuth;
    RedisTemplate<String, String> redisTemplate;

    ServiceWeibo(MapperWeibo mapperWeibo, MapperAuth mapperAuth, RedisTemplate<String, String> redisTemplate) {
        this.mapperWeibo = mapperWeibo;
        this.mapperAuth = mapperAuth;
        this.redisTemplate = redisTemplate;
    }

    public boolean currentUserWeibo(int weiboId, int currentUserId) {

        Weibo weibo = this.mapperWeibo.currentUserWeiboById(weiboId, currentUserId);
        boolean result = weibo != null;
        return result;
    }

    // 显示已关注者的微博
    public ArrayList<Weibo> timelineWeibos(User currentUser) {
        ArrayList<Weibo> weibos = this.mapperWeibo.timelineWeibos(currentUser.getId());

        for (int i = 0; i < weibos.size(); i++) {
            Weibo weibo = weibos.get(i);
            ArrayList<Comment> comments = this.mapperWeibo.commentsFromWeibo(weibo.getId());
            weibo.setOldComments(comments);
        }
        return weibos;
    }

    // 关注者所有微博的 user
    public HashMap<Integer, User> usersFromFollowee(ArrayList<Weibo> weibos) {
        // 选取微博
        HashMap<Integer, User> allFollowee = new HashMap<>();
        User user;
        // 通过微博 id 获取评论
        for (int i = 0; i < weibos.size(); i++) {
            Weibo weibo = weibos.get(i);
            ArrayList<Comment> comments = this.mapperWeibo.commentsFromWeibo(weibo.getId());

            user = this.mapperAuth.userFromId(weibo.getUserId());
            if (user.getAvatar() == null) {
                user.setAvatar("default.jpg");
            }
            allFollowee.put(weibo.getId(), user);

            for (int j = 0; j < comments.size(); j++) {
                Comment comment = comments.get(j);

                user = this.mapperAuth.userFromId(comment.getUserId());
                if (user.getAvatar() == null) {
                    user.setAvatar("default.jpg");
                }
                allFollowee.put(comment.getId(), user);

            }
        }

        return allFollowee;
    }

    // 1、对比当前用户 id 与评论的用户 id
    // 2、通过评论找微博 id ,对比微博用户 id 和当前用户 id
    // 符合其中之一即可
    // 判断当前 comment 的操作者是否为微博的发出者或者评论的发出者
    public boolean currentUserWeiboComment(int commentId, int currentUserId) {
        // 当前编辑用户为评论者
        ArrayList<Comment> comments = this.mapperWeibo.currentUserWeiboComment(commentId, currentUserId, currentUserId);
        boolean result = comments.size() > 0;
        return result;
    }

    // 当前用户的所有微博
//    public ArrayList<WeiboWithComments> currentUserWeibos(User currentUser) {
//        // 选取微博
//        ArrayList<WeiboWithComments> weibos = this.mapperWeibo.currentUserWeibosWithComments(currentUser.getId());
//        return weibos;
//    }
    public ArrayList<Weibo> currentUserWeibos(User currentUser) {
        // 选取微博
        ArrayList<Weibo> weibos = this.mapperWeibo.weibosFromUserId(currentUser.getId());

        // 通过微博 id 获取评论
        for (int i = 0; i < weibos.size(); i++) {
            Weibo weibo = weibos.get(i);
            ArrayList<Comment> comments = this.mapperWeibo.commentsFromWeibo(weibo.getId());
            Utils.log("log comments %s", comments);
            weibo.setOldComments(comments);
        }

        return weibos;
    }

    // 当前用户的所有微博下的评论的 user 头像
    public HashMap<Integer, User> usersFromComments(User currentUser) {
        // 选取微博
        ArrayList<Weibo> weibos = this.mapperWeibo.weibosFromUserId(currentUser.getId());
        HashMap<Integer, User> allCommentUsers = new HashMap<>();

        // 通过微博 id 获取评论
        for (int i = 0; i < weibos.size(); i++) {
            Weibo weibo = weibos.get(i);
            ArrayList<Comment> comments = this.mapperWeibo.commentsFromWeibo(weibo.getId());
            for (int j = 0; j < comments.size(); j++) {
                Comment comment = comments.get(j);
                User user = this.mapperAuth.userFromId(comment.getUserId());
                if (user.getAvatar() == null) {
                    user.setAvatar("default.jpg");
                }
                allCommentUsers.put(comment.getId(), user);
            }
        }

        return allCommentUsers;
    }


    // 为当前用户添加微博
    public void add(String content, User currentUser) {
        this.mapperWeibo.addWeibo(content, currentUser.getId());
    }

    // 为当前微博添加 comment
    public void commentAdd(String content, User currentUser, int weiboId) {
        this.mapperWeibo.addWeiboComment(content, currentUser.getId(), weiboId);
    }

    // 为当前用户添加关注者
    public void follow(User currentUser, int user_id) {
        this.mapperWeibo.addFollowee(currentUser.getId(), user_id);
    }

    // 删除评论
    public void commentDelete(int commentId) {
        this.mapperWeibo.deleteWeiboComment(commentId);
    }

    // 删除微博
    public void delete(int id) {
        this.mapperWeibo.deleteWeiboById(id);
    }

    // 修改微博
    public void update(int id, String content) {
        this.mapperWeibo.updateWeiboById(content, id);
    }

    // 修改评论
    public void commentUpdate(int id, String content) {
        this.mapperWeibo.updateCommentById(content, id);
    }

    // 返回weibo数
    public int weiboCount(int userId) {
        // 加入 redis
        String key = String.valueOf(userId);
        String countString = this.redisTemplate.opsForValue().get(key);
        Utils.log("countString %s ", countString);
        int count = this.mapperWeibo.weiboCountFromUserId(userId);
        Utils.log("weiboCount %s ", count);
        if (countString == null || Integer.parseInt(countString) != count) {
            String result = String.valueOf(count);
            this.redisTemplate.opsForValue().set(key, result);
            return Integer.parseInt(result);
        } else{
            return Integer.parseInt(countString);
        }
//        int weiboCount = this.mapperWeibo.weiboCountFromUserId(userId);
//        return weiboCount;
    }

    // 返回 followee 数
    public int followeeCount(int userId) {
        int followeeCount = this.mapperWeibo.followeeCountFromUserId(userId);
        Utils.log("followeeCount %s ", followeeCount);
        return followeeCount;
    }

}
