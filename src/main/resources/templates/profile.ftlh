<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>profile</title>
</head>
<body>
<a href='/index'>主页</a>
<div>
    <form method="post" action="/avatar/add" enctype="multipart/form-data">
        <input type="file" name="file">
        <button>上传头像</button>
    </form>
</div>
<div>
    <img src='/avatar?file=${user.avatar}' width="200" height="200">
</div>
<div>
    用户名: ${user.username}
</div>
<div>
    密码: ${user.password}
</div>
<div>
    用户权限: ${user.role}
</div>
</body>
</html>
