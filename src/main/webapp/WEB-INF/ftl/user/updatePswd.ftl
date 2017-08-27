<!DOCTYPE html>
<#include "../config/top.ftl">
<#include "../config/left.ftl">
<html>
    <head>
        <meta charset="utf-8">
        <title>资料修改-个人中心</title>
        <link rel="icon" href="${basePath}/images/cyh.ico" type="image/x-icon"/>
        <link rel="shortcut icon" href="${basePath}/images/cyh.ico"/>
        <link rel="stylesheet" href="${basePath}/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="${basePath}/css/layui.css"/>
        <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <@top/>
        <div class="container" style="min-height:300px;margin-top: 60px;">
            <div class="row">
                <@user 1/>
                <div class="col-md-10">
                    <h2>密码修改</h2>
                    <hr>
                    <form id="formId" action="${basePath}/u/updatePswd" method="post">
                        <div class="form-group">
                            <label>原密码</label>
                            <input type="password" name="pswd" id="pswd" class="form-control" placeholder="请输入原密码"/>
                        </div>
                        <div class="form-group">
                            <label>新密码</label>
                            <input type="password" name="newPswd" id="newPswd" class="form-control" placeholder="请输入新密码"/>
                        </div>
                        <div class="form-group">
                            <label>新密码(再输入一次)</label>
                            <input type="password" name="reNewPswd" id="reNewPswd" class="form-control" placeholder="请再次输入新密码"/>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-success">确认修改</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="${basePath}/js/jquery.form-2.82.js"></script>
        <script type="text/javascript">
            $(function () {
                $("#formId").ajaxForm({
                    success:function (result) {
                        if(result && result.status != 200){
                            alert(result.message);
                            return !1;
                        }else {
                            alert('操作成功');
                            $("#formId :password").val('');
                        }
                    },
                    beforeSubmit:function () {
                        if($.trim($("#pswd").val()) == ''){
                            alert("密码不能为空");
                            $("#pswd").parent().removeClass('.has-success').addClass('.has-error');
                            return !1;
                        }else {
                            $("#pswd").parent().removeClass('.has-error').addClass('.has-success');
                        }
                        if($.trim($("#newPswd").val()) == ''){
                            alert("新密码不能为空");
                            $("#newPswd").parent().removeClass('.has-success').addClass('.has-error');
                            return !1;
                        }else {
                            $("#newPswd").parent().removeClass('.has-error').addClass('.has-success');
                        }
                        if($.trim($("#reNewPswd").val()) == ''){
                            alert("请再次输入新密码");
                            $("#reNewPswd").parent().removeClass('.has-success').addClass('.has-error');
                            return !1;
                        }else {
                            $("#reNewPswd").parent().removeClass('.has-error').addClass('.has-success');
                        }
                        if($("#pswd").val() != $("#newPswd").val()){
                            alert("两次密码要相同");
                            return !1;
                        }
                    },
                    dataType:"json",
                    clearForm:false
                });
            });
        </script>
    </body>
</html>