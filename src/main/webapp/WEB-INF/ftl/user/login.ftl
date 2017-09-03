<!DOCTYPE html>
<#assign base=request.contextPath />
<html lang="en" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="icon" href="${basePath}/images/cyh.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${basePath}/images/cyh.ico"/>
    <link rel="stylesheet" href="${basePath}/css/login/reset.css"/>
    <link rel="stylesheet" href="${basePath}/css/login/supersized.css"/>
    <link rel="stylesheet" href="${basePath}/css/login/style.css"/>

</head>
<body id="body">
    <div id="supersized"></div>
    <div class="page-container">
        <h1>登录</h1>
        ${base}
        <form name="_form" method="post">
            <input type="text" name="account" class="username" placeholder="用户名"/>
            <input type="password" name="password" class="password" placeholder="密码"/>
            <label><input id="rememberMe" style="width: 10px;height:10px;" type="checkbox" checked="checked"/>记住我 </label>
            <input type="button" value="登录" id="login"/>
            <input type="button" value="注册" id="register"/>
        </form>
    </div>
    <script src="${basePath}/js/jquery-1.8.2.min.js" type="text/javascript"></script>
    <script src="${basePath}/js/common/supersized.3.2.7.min.js"></script>
    <script src="${basePath}/js/common/supersized-init.js"></script>
    <script src="${basePath}/js/layui/layui.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#login').click(function () {
                var username = $('.username').val();
                var password = $('.password').val();
                var data = {pswd:password,email:username,rememberMe:$("#rememberMe").is(':checked')};
                $.ajax({
                    type:"post",
                    url:"${base}/u/submitLogin/",
                    data:data,
                    dataType:"json",
                    async:true,
                    beforeSend:function () {
                        //layui.layer.msg('登陆中');
                        layui.use(['layer'],function () {
                            var layer = layui.layer
                            layer.msg('登陆中');
                        });
                    },
                    success:function (result) {
                        //layer.close(load);
                        if(result && result.status != 200){
                            layui.use(['layer'],function () {
                                var layer = layui.layer
                                return layer.msg("status:" + result.status +
                                        "  message: " + result.message),!1;
                            });
                        }else {
                            layui.use(['layer'],function () {
                                var layer = layui.layer
                                layer.msg('登陆成功');
                            });
                            setTimeout(function () {
                                window.location.href = result.back_url;
                            },1000)

                        }
                    }
                });
            });
            $("#register").click(function () {
                window.location.href = "u/register";
            });
        });
    </script>
</body>
</html>