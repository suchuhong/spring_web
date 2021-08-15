<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>comment 增加</title>
    <link rel="stylesheet" href="/input.css">
</head>
<body>
<div class="message-container">
    <form action="/comment/add" method="get">
        <input name="id" value="${weibo_id}" type="hidden"/>
        <p class="text">
            <textarea name="content" class="validate[required,length[6,300]] feedback-input" id="comment"></textarea>
        </p>
        <button type="submit">评论</button>
    </form>
</div>
</body>
</html>
