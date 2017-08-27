<html>
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <link rel="icon" href="${basePath}/images/cyh.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${basePath}/images/cyh.ico"/>
    <link rel="stylesheet" href="${basePath}/css/layui.css"/>
    <link rel="stylesheet" href="${basePath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${basePath}/css/login/supersized.css"/>
    <link rel="stylesheet" href="${basePath}/css/login/style.css"/>
    <style>
        #vcode >img{cursor:pointer;margin-bottom: -15px;border-radius:5px;}
    </style>
    <script src="${basePath}/js/jquery-1.8.2.min.js"></script>
    <script src="${basePath}/js/common/supersized.3.2.7.min.js"></script>
    <script src="${basePath}/js/common/supersized-init.js"></script>
    <script src="${basePath}/js/layui/layui.js"></script>
    <script>
        $(document).ready(function () {
            $("#vcode").on("click",'img',function () {
                var i = new Image();
                i.src = "${basePath}/open/getVCode?" + Math.random();
                $(i).replaceAll(this);
            });
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
                            layui.use(['layer'],function () {
                                var layer = layui.layer;
                                layer.msg(result.message);
                            });
                        }else {
                            layui.use(['layer'],function () {
                                var layer = layui.layer;
                                layer.msg('注册成功');
                            });
                        }
                    }    
                );
            });
            $("#login").click(function () {
                window.location.href = "login";
            });

        });

    </script>
</head>
<body>
    <div class="container">
        <div class="form-group">
            <h1>Register</h1>
            <form id="_form" action="${basePath}/u/subRegister" method="post" >
                <input type="text" name="nickname" id="nickname" placeholder="昵称" />
                <input type="text" name="email" id="email" placeholder="邮箱" />
                <input type="password" name="pswd" id="pswd"  placeholder="密码"/>
                <input type="password" name="re_pwd" id="re_pwd"  placeholder="重复密码"/>
                <div id="vcode" style="text-align: left;margin-left: 10px;">
                    <input type="text" name="vcode"  style="width: 100px;margin-left: -8px;margin-right: 8px;" placeholder="验证码"/>
                    <img src="${basePath}/open/getVCode" />
                </div>
                <button type="button" class="register" id="register">注册</button>
                <button type="button" class="login" id="login">登陆</button>
                <div class="error"></div>
            </form>
        </div>
    </div>
</body>
</html>