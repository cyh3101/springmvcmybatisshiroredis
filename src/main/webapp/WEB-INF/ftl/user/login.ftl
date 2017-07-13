<!DOCTYPE html>
<#assign base=request.contextPath />
<html lang="en" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <script src="${basePath}/js/jquery-3.2.1.js" type="text/javascript"></script>
    <script src="${basePath}/js/layui/layui.js"></script>
    <title>Title</title>
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

                        }else {
                            layui.use(['layer'],function () {
                                var layer = layui.layer
                                layer.msg('登陆成功');
                            });
                        }
                    }
                });
            });
        });
    </script>
</head>
<body id="body">
    <div class="page-contain">
        <h1>登录</h1>
        ${base}
        <form name="_form" method="post">
            <input type="text" name="account" class="username" placeholder="用户名"/>
            <input type="password" name="password" class="password" placeholder="密码"/>
            <label><input id="rememberMe" type="checkbox" checked="checked"/>记住我 </label>
            <input type="button" value="登录" id="login"/>
            <input type="button" value="注册" id="register"/>
        </form>
    </div>
</body>
</html>