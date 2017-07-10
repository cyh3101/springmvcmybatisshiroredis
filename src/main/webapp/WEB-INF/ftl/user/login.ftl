<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <script href="${basePath}/js/jquery-3.2.1.js"/>
    <title>Title</title>
    <script type="text/javascript">
        $('#login').click(function () {
            var username = $('username').val();
            var password = $('password').val();
            var data = {pswd:password,email:username,rememberMe:$("rememberMe").is(':checked')};
            $.ajax({
            	type:"post",
            	url:"/u/login/",
            	data:data,
            	dataType:"json",
            	async:true,
                beforeSend:function () {

                }
            });
        });
    </script>
</head>
<body id="body">
    <div class="page-contain">
        <h1>登录</h1>
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