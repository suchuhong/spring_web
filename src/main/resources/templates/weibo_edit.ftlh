<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>weibo 编辑</title>
        <link rel="stylesheet" href="/input.css">
    </head>
    <!-- body 中是浏览器要显示的内容 -->
    <body>
    <div class="message-container">
        <form action="/weibo/update" method="get">
            <input name="id" value="${weibo_id}" type="hidden"/>
            <p class="text">
                <textarea name="content" class="validate[required,length[6,300]] feedback-input" id="comment"></textarea>
            </p>
            <button type="submit">发布</button>
        </form>
    </div>
    </body>
</html>
