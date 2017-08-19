<!DOCTYPE html>
<#include "../config/top.ftl">
<#include "../config/left.ftl">
<html>
    <head>
        <link rel="stylesheet" href="${basePath}/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="${basePath}/css/layui.css"/>
        <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <@top/>
        <div class="container" style="min-height:300px;margin-top: 60px;">
            <div class="row">
                <@user/>
                <div class="col-md-10">
                    <h2>资料修改</h2>
                    <hr>
                    <form action="${basePath}/u/updateSelf" method="post">
                        <input type="hidden" value="${token.id}" name="id"/>
                        <div class="form-group">
                            <label>昵称</label>
                            <input type="text" class="form-control" name="nickname" id="nickname"
                                   value="${token.nickname?default('未设置')}" placeholder="请设置昵称"/>
                        </div>
                        <div class="form-group">
                            <label>Email(不准修改)</label>
                            <input type="text" class="form-control" name="email" id="email"
                                   disabled="disabled" value="${token.email?default('未设置')}" placeholder="请设置email"/>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-success">确认修改</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>