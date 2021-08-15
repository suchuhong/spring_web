<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

<!--    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"-->
<!--          integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">-->
    <!--    导航栏使用以下样式 -->
    <link rel="stylesheet" href="/12.2f97d03e.chunk.css">
    <link rel="stylesheet" href="/13.8c3b56fa.chunk.css">
    <link rel="stylesheet" href="/commons.d40cf249.chunk.css">
    <link rel="stylesheet" href="/styles.9b5b2500.chunk.css">

</head>
<body class>

<div id="__next">
    <header style="width:100%">
        <div class="_2oDcyf _1YyUun _2RZATq">
            <nav class="_3JYrtj">
                <a class="hM7XFL _1OhGeD" href="/login/view">登录</a>
                <a class="hM7XFL _1OhGeD" href="/register/view">注册</a>
                <a class="hM7XFL _1OhGeD" href="/todo/all">todo</a>
                <a class="hM7XFL _1OhGeD" href="/message/all">message</a>
                <a class="hM7XFL _1OhGeD" href="/weibo/all">weibo</a>
                <a class="hM7XFL _1OhGeD" href="/profile">profile</a>
                <a class="hM7XFL _1OhGeD" href="/weibo/timeline">我的关注</a>
                <a class="hM7XFL _1OhGeD" href="/admin/users">admin</a>
            </nav>
        </div>
    </header>

    <div class="_3kbg6I">
        <div class="_3VRLsv" role="main">
            <div class="_gp-ck">
                <#list weibos as weibo>
                <section class="ouvJEz">
                        ${weibo.content}
                </section>
                </#list>
            </div>

            <aside class="_2OwGUo">
                <section class="_3Z3nHf">
                    <div class="_3Oo-T1">
                        <a class="_1b5rv9 _1OhGeD" href="#" target="_blank" rel="noopener noreferrer">
                            <img class="_3T9iJQ" src="/avatar?file=${avatar}">
                        </a>
                        <div class="_32ZTTG">
                            <div class="_2O0T_w">
                                <div class="_2v-h3G">
                                    <span class="_2vh4fr">
                                        <a class="_1OhGeD" href="#" target="_blank"
                                           rel="noopener noreferrer">${message}</a>
                                    </span>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="_19DgIp"></div>
                    <div class="_26Hhi2" role="listitem">
                        <div class="_3TNGId">
                            <a class="_2ER8Tt _1OhGeD" href="#" target="_blank" rel="noopener noreferrer">
                                发布数 ${weibo_count}
                            </a>
                        </div>
                        <div class="_3TNGId">
                            <a class="_2ER8Tt _1OhGeD" href="#" target="_blank" rel="noopener noreferrer">
                                关注数 ${followee_count}
                            </a>
                        </div>
                    </div>
                </section>

            </aside>
        </div>
    </div>
</div>


</body>
</html>