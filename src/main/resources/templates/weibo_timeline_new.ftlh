<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>timeline</title>
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
    <ul id="comments-list" class="comments-list">
        <li>
        <#list weibos as weibo>
            <div class="comment-main-level">
                <#list all_followee as key, value>
                    <#if key == weibo.getId()>
                    <!-- Avatar -->
                    <div class="comment-avatar"><img src="/avatar?file=${value.getAvatar()}" alt=""></div>
                    <!-- Contenedor del Comentario -->
                    <div class="comment-box">
                        <div class="comment-head">
                            <h6 class="comment-name by-author"><a href="#">${value.getUsername()}</a></h6>
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
                    </div>
                    </#if>
                </#list>
            </div>
            <#list weibo.oldComments as comment>
                <ul class="comments-list reply-list">
                    <li>
                        <!-- Avatar -->
                        <#--                    key 只能为 String-->
                        <#list all_followee as key, value>
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
