<html>
<head>
    <title>register</title>
    <link href="${basePath}/css/layui.css"/>
    <link href="${basePath}/css/bootstrap.min.css"/>
    <script src="${basePath}/js/jquery-3.2.1.js"></script>
    <script src="${basePath}/js/layui/layui.js"></script>
    <script>
        $(document).ready(function () {
            $("#register").click(function () {
                var pswd = $('#pswd').val();
                var re_pwd = $('#re_pwd').val();
                if(pswd != re_pwd){
                    layui.use(['layer'],function () {
                        var layer = layui.layer;
                        return layer.msg('密码不一致'),!1;
                    });
                }
                $.post(
                    "${basePath}/u/subRegister",
                    $('#_form').serialize(),
                    function (result) {
                        if(result && result.status != 200){

                        }else {
                            layui.use(['layer'],function () {
                                var layer = layui.layer;
                                layer.msg('注册成功');

                            });
                        }
                    }    
                );
            });

        });

    </script>
</head>
<body>
    <div class="container">
        <div class="form-group">
            <h1>Register</h1>
            <form id="_form" action="${basePath}/u/subRegister" method="post" class="form-group">
                <input type="text" name="nickname" id="nickname" placeholder="昵称" class="form-control"/>
                <input type="text" name="email" id="email" placeholder="邮箱" class="form-control"/>
                <input type="password" name="pswd" id="pswd" class="form-control"/>
                <input type="password" name="re_pwd" id="re_pwd" class="form-control"/>

                <button type="button" class="register form-control" id="register">注册</button>
                <button type="button" class="login form-control" id="login">登陆</button>
                <div class="error"></div>
            </form>
        </div>
    </div>
</body>
</html>