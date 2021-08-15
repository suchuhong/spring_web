<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>message</title>
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
<div class="message-container">

    <form action="/message/add/get" method="get">
        <p class="text">
            <textarea name="content" class="validate[required,length[6,300]] feedback-input" id="comment"></textarea>
        </p>
        <button type="submit">GET 提交</button>
    </form>
    <br>
    <form action="/message/add/post" method="post">
        <p class="text">
            <textarea name="content" class="validate[required,length[6,300]] feedback-input" id="comment"></textarea>
        </p>
        <button type="submit">POST 提交</button>
    </form>
    <br>
    <#list messages as message>
        <h4>
            作者: ${message.author}, 内容: ${message.content}
        </h4>
    </#list>
</div>

</body>
</html>