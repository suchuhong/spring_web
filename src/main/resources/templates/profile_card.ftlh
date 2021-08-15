<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>profile</title>
    <link rel="stylesheet" href="/profile_card.css">
</head>
<body>

    <div class="profile-card js-profile-card">
        <div class="profile-card__img">
            <img src="/avatar?file=${user.avatar}" alt="profile card">
        </div>

        <div class="profile-card__cnt js-profile-cnt">
            <div class="profile-card__name">用户名：${user.username}</div>
            <div class="profile-card__txt">密码：${user.password} </div>
            <div class="profile-card__txt">用户权限：${user.role} </div>
            <div>
                <form method="post" action="/avatar/add" enctype="multipart/form-data">
                    <input type="file" name="file">
                    <button>上传头像</button>
                </form>
            </div>

            <div class="profile-card-social">
<#--                target="_blank" 跳转新标签-->
<#--                <a href="/index" class="profile-card-social__item facebook" target="_blank">-->
                <a href="/index" class="profile-card-social__item facebook">
                  <span class="icon-font">
                      主页
                  </span>
                </a>

                <a href="/weibo/all" class="profile-card-social__item twitter">
                  <span class="icon-font">
                      weibo
                  </span>
                </a>

                <a href="/todo/all" class="profile-card-social__item instagram">
                  <span class="icon-font">
                      todo
                  </span>
                </a>

<#--                <a href="https://www.behance.net/iaMuhammedErdem" class="profile-card-social__item behance" target="_blank">-->
<#--                  <span class="icon-font">-->
<#--                      -->
<#--                  </span>-->
<#--                </a>-->

<#--                <a href="https://github.com/muhammederdem" class="profile-card-social__item github" target="_blank">-->
<#--                  <span class="icon-font">-->
<#--                      -->
<#--                  </span>-->
<#--                </a>-->

<#--                <a href="https://codepen.io/JavaScriptJunkie" class="profile-card-social__item codepen" target="_blank">-->
<#--                  <span class="icon-font">-->
<#--                      -->
<#--                  </span>-->
<#--                </a>-->

<#--                <a href="http://muhammederdem.com.tr/" class="profile-card-social__item link" target="_blank">-->
<#--                  <span class="icon-font">-->
<#--                      -->
<#--                  </span>-->
<#--                </a>-->

            </div>

            <div class="profile-card-ctr">
                <button class="profile-card__button button--blue js-message-btn">Message</button>
                <button class="profile-card__button button--orange">Follow</button>
            </div>
        </div>

    </div>

</body>
</html>