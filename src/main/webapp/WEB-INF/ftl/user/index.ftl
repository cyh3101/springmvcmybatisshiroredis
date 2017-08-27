<!DOCTYPE html>
<#assign base=request.contextPath/>
<#include "../config/top.ftl">
<#include "../config/left.ftl">
<html lang="en" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <title>${token.nickname}-个人中心</title>
    <link rel="icon" href="${basePath}/images/cyh.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${basePath}/images/cyh.ico"/>
    <link rel="stylesheet" href="${basePath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${basePath}/css/layui.css"/>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body data-target="#one" data-spy="scroll">
    <@top />
    <div class="container" style="min-height:300px;margin-top: 60px;">

        <div class="row">
            <@user 1/>
            <div class="col-md-10">
                <h2>个人资料</h2>
                <hr>
                <table class="table table-bordered">
                    <tr>
                        <th>昵称</th>
                        <td>${token.nickname?default('未设置')}</td>
                    </tr>
                    <tr>
                        <th>Email/账号</th>
                        <td>${token.email}</td>
                    </tr>
                    <tr>
                        <th>创建时间</th>
                        <td>${token.createTime?string('yyyy-MM-dd HH:mm')}</td>
                    </tr>
                    <tr>
                        <th>最后登录时间</th>
                        <td>${token.lastLoginTime?string('yyyy-MM-dd HH:mm')}</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <script src="${basePath}/js/jquery-1.8.2.min.js" type="text/javascript"/>
    <script src="${basePath}/js/jquery-3.2.1.js" type="text/javascript"/>
    <script src="${basePath}/js/bootstrap.min.js" type="text/javascript"/>
    <script src="${basePath}/js/layui/layui.js" type="text/javascript"/>
</body>
</html>