<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>comment</title>
    <link rel="stylesheet" href="/comment.css">
    <link rel="stylesheet" href="/commons.d40cf249.chunk.css">
    <link rel="stylesheet" href="/input.css">
</head>
<body>
<header style="width:100%">
    <div class="_2oDcyf _1YyUun _2RZATq">
        <nav class="_3JYrtj">
            <a class="hM7XFL _1OhGeD" href="/index">主页</a>
        </nav>
    </div>
</header>
<div class="comments-container">
    <p class="text">
        <a href="/weibo/follow?user_id=${current_user.getId()}">
            <button type="submit">关注</button>
        </a>
    </p>

    <form action="/weibo/add" method="get">
        <p class="text">
            <textarea name="content" class="validate[required,length[6,300]] feedback-input" id="comment"></textarea>
        </p>
        <button type="submit">发布</button>
    </form>

    <ul id="comments-list" class="comments-list">
        <li>
        <#list weibos as weibo>
            <div class="comment-main-level">
                <!-- Avatar -->
                <div class="comment-avatar"><img src="/avatar?file=${avatar}" alt=""></div>
                <!-- Contenedor del Comentario -->
                <div class="comment-box">
                    <div class="comment-head">
                        <h6 class="comment-name by-author"><a href="#">${current_user.getUsername()}</a></h6>
<#--                        <span>时间</span>-->
                        <i class="fa fa-reply"></i>
                        <i class="fa fa-heart"></i>
                    </div>
                    <div class="comment-content" id="${weibo.id}">
                        ${weibo.content}
                    </div>
                    <button>
                        <a href=/comment/new?id=${weibo.id}>添加评论</a>
                    </button>
                    <button>
                        <a href=/weibo/delete?id=${weibo.id}>删除</a>
                    </button>
                    <button>
                        <a href=/weibo/edit?id=${weibo.id}>编辑</a>
                    </button>
                </div>
            </div>
            <#list weibo.oldComments as comment>
            <ul class="comments-list reply-list">
                <li>
                    <!-- Avatar -->
<#--                    key 只能为 String-->
                    <#list all_comment_users as key, value>
                        <#if key == comment.getId()>
                        <div class="comment-avatar"><img src="/avatar?file=${value.getAvatar()}" alt=""></div>
                        <!-- Contenedor del Comentario -->
                        <div class="comment-box">
                            <div class="comment-head">
                                <h6 class="comment-name"><a href="#">${value.getUsername()}</a></h6>
    <#--                            <span>时间</span>-->
                                <i class="fa fa-reply"></i>
                                <i class="fa fa-heart"></i>
                            </div>
                            <div class="comment-content" id="${comment.getId()}">
                                ${comment.getContent()}
                            </div>
                            <button>
                                <a href=/comment/delete?id=${comment.getId()}>删除</a>
                            </button>
                            <button>
                                <a href=/comment/edit?id=${comment.getId()}>编辑</a>
                            </button>
                        </div>
                        </#if>
                    </#list>
                </li>
            </ul>
            </#list>
        </li>
        </#list>
    </ul>
</div>
</body>
</html>